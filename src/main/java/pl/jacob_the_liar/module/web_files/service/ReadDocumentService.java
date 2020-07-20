package pl.jacob_the_liar.module.web_files.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jacob_the_liar.module.web_files.component.SaltComponent;
import pl.jacob_the_liar.module.web_files.exception.DocumentNotFoundException;
import pl.jacob_the_liar.module.web_files.model.Document;
import pl.jacob_the_liar.module.web_files.model.DocumentDownload;
import pl.jacob_the_liar.module.web_files.repository.DocumentRepository;
import pl.jacob_the_liar.module.web_files.utils.HashIdDocument;

import java.time.LocalDateTime;
import java.util.Optional;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-07-20 21:00
 * *
 * @className: ReadDocumentService
 * *
 * *
 ******************************************************/
@Service
@RequiredArgsConstructor
public class ReadDocumentService{
    
    private final DocumentRepository documentRepository;
    private final SaltComponent salt;
    
    
    public DocumentDownload getDocument(String fileId){
        
        HashIdDocument hashId = new HashIdDocument(salt.get());
        long documentId = hashId.decode(fileId);
        
        Optional<Document> documentOpt = documentRepository.findById(documentId);
        if (documentOpt.isPresent()) {
            documentOpt.get().setLastUse(LocalDateTime.now());
            documentRepository.save(documentOpt.get());
            return new DocumentDownload(documentOpt.get());
        }
        
        throw new DocumentNotFoundException(fileId);
    }
}
