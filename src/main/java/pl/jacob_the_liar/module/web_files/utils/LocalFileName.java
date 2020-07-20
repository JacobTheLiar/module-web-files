package pl.jacob_the_liar.module.web_files.utils;


import org.apache.commons.io.FilenameUtils;
import pl.jacob_the_liar.module.web_files.model.Document;
import pl.jacob_the_liar.module.web_files.utils.consumer.FileNameConsumer;

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

public class LocalFileName implements FileNameConsumer{
    
    @Override
    public void accept(Document document, String originalFileName){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        StringBuilder newName = new StringBuilder();
        newName.append(LocalDateTime.now().format(formatter))
                .append(".")
                .append(FilenameUtils.getExtension(originalFileName));
        document.setLocalName(newName.toString());
    }
}
