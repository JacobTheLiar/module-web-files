package pl.jacob_the_liar.module.web_files.utils;


import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.BooleanSupplier;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-06-29 18:03
 * *
 * @className: StoreDocument
 * *
 * *
 ******************************************************/
@RequiredArgsConstructor
public class StoreDocument implements BooleanSupplier{
    
    private final DocumentStore document;
    private final DocumentBytes documentBytes;
    
    
    public boolean getAsBoolean(){
        Path fileNameAndPath = Paths.get(document.getLocalPath(), document.getLocalName());
        try {
            Files.write(fileNameAndPath, documentBytes.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
