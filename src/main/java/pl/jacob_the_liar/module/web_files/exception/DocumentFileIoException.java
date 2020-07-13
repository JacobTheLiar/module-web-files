package pl.jacob_the_liar.module.web_files.exception;


import java.io.IOException;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-07-13 20:48
 * *
 * @className: DocumentFileIoException
 * *
 * *
 ******************************************************/
public class DocumentFileIoException extends RuntimeException{
    
    public DocumentFileIoException(IOException e){
        super(e);
    }
}
