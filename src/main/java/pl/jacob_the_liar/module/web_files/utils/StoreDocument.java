package pl.jacob_the_liar.module.web_files.utils;


import pl.jacob_the_liar.module.web_files.exception.DocumentFileIoException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-06-29 18:03
 * *
 * @className: StoreDocument
 * *
 * *
 ******************************************************/
public class StoreDocument implements StoreConsumer{
    
    public void accept(DocumentStore document, DocumentBytes documentBytes){
        Path fileNameAndPath = Paths.get(document.getLocalPath(), document.getLocalName());
        try {
            Files.write(fileNameAndPath, documentBytes.getBytes());
        } catch (IOException e) {
            throw new DocumentFileIoException(e);
        }
    }
}
