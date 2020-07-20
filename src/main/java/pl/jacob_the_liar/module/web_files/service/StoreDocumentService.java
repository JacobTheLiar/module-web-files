package pl.jacob_the_liar.module.web_files.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.jacob_the_liar.module.web_files.component.SaltComponent;
import pl.jacob_the_liar.module.web_files.component.StoragePathComponent;
import pl.jacob_the_liar.module.web_files.model.Document;
import pl.jacob_the_liar.module.web_files.model.DocumentInfo;
import pl.jacob_the_liar.module.web_files.repository.DocumentRepository;
import pl.jacob_the_liar.module.web_files.utils.*;
import pl.jacob_the_liar.module.web_files.utils.consumer.DocumentChecksumConsumer;
import pl.jacob_the_liar.module.web_files.utils.consumer.StoreConsumer;
import pl.jacob_the_liar.module.web_files.utils.consumer.TokenConsumer;

import java.time.LocalDateTime;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-07-20 20:57
 * *
 * @className: StoreDocumentService
 * *
 * *
 ******************************************************/
@Service
@RequiredArgsConstructor
public class StoreDocumentService{
    
    private final DocumentRepository documentRepository;
    private final SaltComponent salt;
    private final StoragePathComponent storagePath;
    
    
    public DocumentInfo storeDocument(MultipartFile file, DocumentRequest request){
        
        Document document = new Document();
        document.setOriginalName(file.getOriginalFilename());
        document.setContentType(file.getContentType());
        document.setLocalPath(storagePath.get());
        document.setOwnerIp(request.getRemoteHost());
        document.setStoreTime(LocalDateTime.now());
        
        LocalFileName nameMaker = new LocalFileName();
        nameMaker.accept(document, file.getOriginalFilename());
        
        StoreConsumer storeDocument = new StoreDocument();
        storeDocument.accept(document, new MultipartFileBytes(file));
        
        DocumentChecksumConsumer checksum = new DocumentChecksum();
        checksum.accept(document);
        
        TokenConsumer tokenConsumer = new TokenDocument();
        tokenConsumer.accept(document, request);
        
        documentRepository.save(document);
        
        return new DocumentInfo(document, salt.get(), request.getRequestURL());
    }
}
