package pl.jacob_the_liar.module.web_files.utils;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-06-29 18:20
 * *
 * @className: ChecksumInfo
 * *
 * *
 ******************************************************/
public interface ChecksumInfo{
    
    String getLocalName();
    
    String getLocalPath();
    
    void setHash(String hash);
}
