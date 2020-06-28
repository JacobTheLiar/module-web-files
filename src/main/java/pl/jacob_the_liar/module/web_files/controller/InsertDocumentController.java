package pl.jacob_the_liar.module.web_files.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.jacob_the_liar.module.web_files.model.Document;
import pl.jacob_the_liar.module.web_files.service.DocumentService;

import javax.servlet.http.HttpServletRequest;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-06-28 18:36
 * *
 * @className: InsertDocumentController
 * *
 * *
 ******************************************************/

@RestController
@RequestMapping("${uri.insert}")
@RequiredArgsConstructor
public class InsertDocumentController{
    
    private final DocumentService documentService;
    
    
    @PostMapping
    public Document insertDocument(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        String remoteAddress = request.getRemoteAddr();
        return documentService.storeDocument(file, remoteAddress);
    }
}
