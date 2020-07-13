package pl.jacob_the_liar.module.web_files.model;


import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-06-29 18:50
 * *
 * @className: DocumentDownload
 * *
 * *
 ******************************************************/
@RequiredArgsConstructor
public class DocumentDownload{
    
    private final Document document;
    
    
    public String getContentType(){
        return document.getContentType();
    }
    
    
    public String getOriginalName(){
        return document.getOriginalName();
    }
    
    
    public byte[] getData() throws IOException{
        File file = new File(document.getLocalPath() + document.getLocalName());
        return Files.readAllBytes(file.toPath());
    }
}
