package pl.jacob_the_liar.module.web_files.utils;


import lombok.RequiredArgsConstructor;

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
@RequiredArgsConstructor
public class DocumentChecksum{
    
    private final ChecksumInfo document;
    
    
    private static String getFileChecksum(MessageDigest digest, File file) throws IOException{
        //Get file input stream for reading the file content
        FileInputStream fis = new FileInputStream(file);
        
        //Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;
        
        //Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        }
        ;
        
        //close the stream; We don't need it now.
        fis.close();
        
        //Get the hash's bytes
        byte[] bytes = digest.digest();
        
        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        //return complete hash
        return sb.toString();
    }
    
    
    public boolean proceedChecksum(){
        
        File file = new File(document.getLocalPath() + document.getLocalName());
        MessageDigest md5Digest = null;
        try {
            md5Digest = MessageDigest.getInstance("MD5");
            document.setHash(getFileChecksum(md5Digest, file));
        } catch (NoSuchAlgorithmException | IOException e) {
            return false;
        }
        return true;
    }
}
