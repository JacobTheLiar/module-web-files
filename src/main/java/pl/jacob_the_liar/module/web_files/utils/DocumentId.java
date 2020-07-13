package pl.jacob_the_liar.module.web_files.utils;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-07-09 19:50
 * *
 * @className: DocumentID
 * *
 * *
 ******************************************************/
public interface DocumentId{
    
    String encode(long id);
    
    long decode(String hashId);
}
