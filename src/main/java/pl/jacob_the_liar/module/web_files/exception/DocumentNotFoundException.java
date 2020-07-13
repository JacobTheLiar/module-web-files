package pl.jacob_the_liar.module.web_files.exception;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-07-13 20:19
 * *
 * @className: DocumentNotFoundException
 * *
 * *
 ******************************************************/
public class DocumentNotFoundException extends RuntimeException{
    
    public DocumentNotFoundException(String message){
        super(message);
    }
}
