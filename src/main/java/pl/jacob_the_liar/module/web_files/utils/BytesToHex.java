package pl.jacob_the_liar.module.web_files.utils;


import javax.xml.bind.DatatypeConverter;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-07-20 20:21
 * *
 * @className: BytesToHex
 * *
 * *
 ******************************************************/
public class BytesToHex{
    
    private BytesToHex(){
    }
    
    
    public static String convert(byte[] data){
        return DatatypeConverter.printHexBinary(data).toLowerCase();
    }
}
