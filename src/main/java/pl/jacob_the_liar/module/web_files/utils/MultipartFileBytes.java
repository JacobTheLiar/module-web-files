package pl.jacob_the_liar.module.web_files.utils;


import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-06-29 18:13
 * *
 * @className: MultipartFileBytes
 * *
 * *
 ******************************************************/
@RequiredArgsConstructor
public class MultipartFileBytes implements DocumentBytes{
    
    public final MultipartFile file;
    
    
    @Override
    public byte[] getBytes() throws IOException{
        return file.getBytes();
    }
}
