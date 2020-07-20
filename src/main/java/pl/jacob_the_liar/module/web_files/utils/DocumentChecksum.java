package pl.jacob_the_liar.module.web_files.utils;


import pl.jacob_the_liar.module.web_files.exception.DocumentFileIoException;
import pl.jacob_the_liar.module.web_files.exception.NoSuchAlgorithmForDocumentException;
import pl.jacob_the_liar.module.web_files.utils.consumer.DocumentChecksumConsumer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-06-28 20:51
 * *
 * @className: DocumentChecksum
 * *
 * *
 ******************************************************/
public class DocumentChecksum implements DocumentChecksumConsumer{
    
    private static String getFileChecksum(MessageDigest digest, File file) throws IOException{
        
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] byteArray = new byte[1024];
            int bytesCount;
            while ((bytesCount = fis.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }
        }
        return BytesToHex.convert(digest.digest());
    }
    
    
    public void accept(DocumentChecksumInfo document){
        File file = new File(document.getLocalPath() + document.getLocalName());
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            document.setHash(getFileChecksum(digest, file));
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmForDocumentException(e);
        } catch (IOException e) {
            throw new DocumentFileIoException(e);
        }
    }
}
