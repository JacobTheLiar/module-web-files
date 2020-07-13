package pl.jacob_the_liar.module.web_files.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.jacob_the_liar.module.web_files.exception.DocumentNotFoundException;
import pl.jacob_the_liar.module.web_files.model.Document;
import pl.jacob_the_liar.module.web_files.model.DocumentDownload;
import pl.jacob_the_liar.module.web_files.model.DocumentInfo;
import pl.jacob_the_liar.module.web_files.repository.DocumentRepository;
import pl.jacob_the_liar.module.web_files.utils.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.BooleanSupplier;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-06-28 18:27
 * *
 * @className: DocumentService
 * *
 * *
 ******************************************************/
@Service
@RequiredArgsConstructor
public class DocumentService{
    
    private final DocumentRepository documentRepository;
    private static final String DOCUMENT_NOT_FOUND = "Document not found! [id#%s]";
    private final Environment environment;
    @Value("${hashids.salt}")
    private final String salt;
    
    
    public DocumentInfo storeDocument(MultipartFile file, HttpServletRequest request){
        
        Document document = new Document();
        
        document.setOriginalName(file.getOriginalFilename());
        document.setContentType(file.getContentType());
        document.setLocalPath(environment.getProperty("storage.path"));
        
        FileNameMaker nameMaker = new FileNameMaker(file.getOriginalFilename());
        document.setLocalName(nameMaker.getNewFileName());
        
        document.setOwnerIp(request.getRemoteHost());
        document.setStoreTime(LocalDateTime.now());
        
        BooleanSupplier storeDocument = new StoreDocument(document, new MultipartFileBytes(file));
        storeDocument.getAsBoolean();
        
        DocumentChecksum checksum = new DocumentChecksum(document);
        checksum.proceedChecksum();
        
        documentRepository.save(document);
        
        return new DocumentInfo(document, salt, request.getRequestURL().toString());
    }
    
    
    public DocumentDownload getDocument(String fileId){
    
        HashIdDocument hashId = new HashIdDocument(salt);
        long documentId = hashId.decode(fileId);
    
        Optional<Document> documentOpt = documentRepository.findById(documentId);
        if (documentOpt.isPresent()) {
            documentOpt.get().setLastUse(LocalDateTime.now());
            documentRepository.save(documentOpt.get());
            return new DocumentDownload(documentOpt.get());
        }
    
        throw new DocumentNotFoundException(String.format(DOCUMENT_NOT_FOUND, fileId));
    }
    
    
    public void deleteDocument(String fileId){
    
        HashIdDocument hashId = new HashIdDocument(salt);
        long documentId = hashId.decode(fileId);
    
        Optional<Document> documentOpt = documentRepository.findById(documentId);
    
        if (documentOpt.isPresent()) {
            Document document = documentOpt.get();
            document.setDeleted(LocalDateTime.now());
            documentRepository.save(document);
            return;
        }
        
        throw new DocumentNotFoundException(String.format(DOCUMENT_NOT_FOUND, fileId));
    }
    
    
    public DocumentInfo aboutDocument(String fileId, HttpServletRequest request){
        HashIdDocument hashId = new HashIdDocument(salt);
    
        Optional<Document> documentOpt = documentRepository.findById(hashId.decode(fileId));
        if (documentOpt.isPresent()) {
            Document document = documentOpt.get();
            return new DocumentInfo(document, salt, request.getRequestURL().toString());
        }
        
        throw new DocumentNotFoundException(String.format(DOCUMENT_NOT_FOUND, fileId));
    }
}
