package pl.jacob_the_liar.module.web_files.controller;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jacob_the_liar.module.web_files.model.DocumentDownload;
import pl.jacob_the_liar.module.web_files.service.ReadDocumentService;

import java.io.IOException;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-06-27 10:08
 * *
 * @className: GetFileController
 * *
 * *
 ******************************************************/
@RequiredArgsConstructor
@RestController
@RequestMapping("${uri.get}")
public class GetDocumentController{
    
    private final ReadDocumentService documentService;
    
    
    @GetMapping
    public ResponseEntity<ByteArrayResource> getFile(@NonNull @PathVariable String fileId){
        DocumentDownload download = documentService.getDocument(fileId);
        
        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(download.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment:filename=\"" + download.getOriginalName() + "\"")
                    .body(new ByteArrayResource(download.getData()));
        } catch (IOException e) {
            return ResponseEntity.noContent().build();
        }
    }
}
