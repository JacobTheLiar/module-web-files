package pl.jacob_the_liar.module.web_files.controller;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jacob_the_liar.module.web_files.service.DeleteDocumentService;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-07-09 18:49
 * *
 * @className: DeleteDocumentController
 * *
 * *
 ******************************************************/
@RestController
@RequestMapping("${uri.delete}")
@RequiredArgsConstructor
public class DeleteDocumentController{
    
    private final DeleteDocumentService documentService;
    
    
    @DeleteMapping
    public void deleteDocument(@NonNull @PathVariable String fileId){
        documentService.deleteDocument(fileId);
    }
}
