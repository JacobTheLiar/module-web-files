package pl.jacob_the_liar.module.web_files.utils;


import pl.jacob_the_liar.module.web_files.exception.DocumentFileIoException;
import pl.jacob_the_liar.module.web_files.exception.NoSuchAlgorithmForDocumentException;

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
public class DocumentChecksum implements ChecksumConsumer{
    
    private static String getFileChecksum(MessageDigest digest, File file) throws IOException{
        FileInputStream fis = new FileInputStream(file);
        StringBuilder sb = new StringBuilder();
        try {
            byte[] byteArray = new byte[1024];
            int bytesCount;
            
            while ((bytesCount = fis.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }
        
            byte[] bytes = digest.digest();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
        } finally {
            fis.close();
        }
        return sb.toString();
    }
    
    
    public void accept(ChecksumInfo document){
        File file = new File(document.getLocalPath() + document.getLocalName());
        MessageDigest md5Digest;
        try {
            md5Digest = MessageDigest.getInstance("MD5");
            document.setHash(getFileChecksum(md5Digest, file));
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmForDocumentException(e);
        } catch (IOException e) {
            throw new DocumentFileIoException(e);
        }
    }
}
