package pl.jacob_the_liar.module.web_files.utils;


import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-06-29 18:28
 * *
 * @className: FileNameMaker
 * *
 * *
 ******************************************************/
@RequiredArgsConstructor
public class FileNameMaker{
    
    private final String originalFileName;
    
    
    public String getNewFileName(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        StringBuilder newName = new StringBuilder();
        newName.append(LocalDateTime.now().format(formatter))
                .append(".")
                .append(FilenameUtils.getExtension(originalFileName));
        
        return newName.toString();
    }
}
