package pl.jacob_the_liar.module.web_files.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jacob_the_liar.module.web_files.component.SaltComponent;
import pl.jacob_the_liar.module.web_files.exception.DocumentNotFoundException;
import pl.jacob_the_liar.module.web_files.model.Document;
import pl.jacob_the_liar.module.web_files.repository.DocumentRepository;
import pl.jacob_the_liar.module.web_files.utils.HashIdDocument;

import java.time.LocalDateTime;
import java.util.Optional;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-07-20 21:06
 * *
 * @className: DeleteDocumentService
 * *
 * *
 ******************************************************/
@Service
@RequiredArgsConstructor
public class DeleteDocumentService{
    
    private final DocumentRepository documentRepository;
    private final SaltComponent salt;
    
    
    public void deleteDocument(String fileId){
        
        HashIdDocument hashId = new HashIdDocument(salt.get());
        long documentId = hashId.decode(fileId);
        
        Optional<Document> documentOpt = documentRepository.findById(documentId);
        
        if (documentOpt.isPresent()) {
            Document document = documentOpt.get();
            document.setDeleted(LocalDateTime.now());
            documentRepository.save(document);
            return;
        }
        
        throw new DocumentNotFoundException(fileId);
    }
}
