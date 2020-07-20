package pl.jacob_the_liar.module.web_files.utils;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-06-29 18:20
 * *
 * @className: DocumentChecksumInfo
 * *
 * *
 ******************************************************/
public interface DocumentChecksumInfo{
    
    String getLocalName();
    
    String getLocalPath();
    
    void setHash(String hash);
}
