package pl.jacob_the_liar.module.web_files.exception;


import java.security.NoSuchAlgorithmException;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-07-13 20:49
 * *
 * @className: NoSuchAlgorithmForDocumentException
 * *
 * *
 ******************************************************/
public class NoSuchAlgorithmForDocumentException extends RuntimeException{
    
    public NoSuchAlgorithmForDocumentException(NoSuchAlgorithmException e){
        super(e);
    }
}
