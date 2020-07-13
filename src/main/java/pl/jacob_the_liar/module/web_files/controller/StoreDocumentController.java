package pl.jacob_the_liar.module.web_files.controller;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.jacob_the_liar.module.web_files.model.DocumentInfo;
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
@RequestMapping("${uri.store}")
@RequiredArgsConstructor
public class StoreDocumentController{
    
    private final DocumentService documentService;
    
    
    @PostMapping
    public DocumentInfo storeDocument(@NonNull @RequestParam("file") MultipartFile file, HttpServletRequest request){
        return documentService.storeDocument(file, request);
    }
}
