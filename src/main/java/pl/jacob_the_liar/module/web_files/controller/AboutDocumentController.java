package pl.jacob_the_liar.module.web_files.controller;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jacob_the_liar.module.web_files.model.DocumentInfo;
import pl.jacob_the_liar.module.web_files.service.DocumentService;

import javax.servlet.http.HttpServletRequest;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-07-09 19:14
 * *
 * @className: AboutDocumentController
 * *
 * *
 ******************************************************/
@RequiredArgsConstructor
@RestController
@RequestMapping("${uri.about}")
public class AboutDocumentController{
    
    private final DocumentService documentService;
    
    
    @GetMapping
    public DocumentInfo aboutDocument(@NonNull @PathVariable String fileId, HttpServletRequest request){
        return documentService.aboutDocument(fileId, request);
    }
}
