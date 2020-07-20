package pl.jacob_the_liar.module.web_files.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jacob_the_liar.module.web_files.component.SaltComponent;
import pl.jacob_the_liar.module.web_files.exception.DocumentNotFoundException;
import pl.jacob_the_liar.module.web_files.model.Document;
import pl.jacob_the_liar.module.web_files.model.DocumentInfo;
import pl.jacob_the_liar.module.web_files.repository.DocumentRepository;
import pl.jacob_the_liar.module.web_files.utils.HashIdDocument;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-07-20 21:07
 * *
 * @className: AboutDocumentService
 * *
 * *
 ******************************************************/
@Service
@RequiredArgsConstructor
public class AboutDocumentService{
    
    private final DocumentRepository documentRepository;
    private final SaltComponent salt;
    
    
    public DocumentInfo aboutDocument(String fileId, HttpServletRequest request){
        
        HashIdDocument hashId = new HashIdDocument(salt.get());
        
        Optional<Document> documentOpt = documentRepository.findById(hashId.decode(fileId));
        if (documentOpt.isPresent()) {
            Document document = documentOpt.get();
            return new DocumentInfo(document, salt.get(), request.getRequestURL().toString());
        }
        
        throw new DocumentNotFoundException(fileId);
    }
}
