package pl.jacob_the_liar.module.web_files.utils;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-07-20 20:48
 * *
 * @className: DocumentRequest
 * *
 * *
 ******************************************************/
public interface DocumentRequest{
    
    String getRemoteHost();
    
    String getToken();
    
    String getRequestURL();
}
