package pl.jacob_the_liar.module.web_files.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.jacob_the_liar.module.web_files.model.Document;
import pl.jacob_the_liar.module.web_files.model.DocumentInfo;
import pl.jacob_the_liar.module.web_files.repository.DocumentRepository;
import pl.jacob_the_liar.module.web_files.utils.DocumentChecksum;
import pl.jacob_the_liar.module.web_files.utils.FileNameMaker;
import pl.jacob_the_liar.module.web_files.utils.MultipartFileBytes;
import pl.jacob_the_liar.module.web_files.utils.StoreDocument;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


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
    
    @Value("${storage.path}")
    private String localPath;
    @Value("${hashids.salt}")
    private String hashidsSalt;
    
    
    public DocumentInfo storeDocument(MultipartFile file, HttpServletRequest request){
        
        Document document = new Document();
        
        document.setOriginalName(file.getOriginalFilename());
        document.setContentType(file.getContentType());
        document.setLocalPath(localPath);
        
        FileNameMaker nameMaker = new FileNameMaker(file.getOriginalFilename());
        document.setLocalName(nameMaker.getNewFileName());
        
        document.setOwnerIp(request.getRemoteHost());
        document.setStoreTime(LocalDateTime.now());
        
        StoreDocument storeDocument = new StoreDocument(document, new MultipartFileBytes(file));
        storeDocument.proceed();
        
        DocumentChecksum checksum = new DocumentChecksum(document);
        checksum.proceedChecksum();
        
        documentRepository.save(document);
        
        DocumentInfo info = new DocumentInfo(document, hashidsSalt, request.getRequestURL().toString());
        
        return info;
    }
}
