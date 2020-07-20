package pl.jacob_the_liar.module.web_files.utils.consumer;


import pl.jacob_the_liar.module.web_files.model.Document;
import pl.jacob_the_liar.module.web_files.utils.DocumentRequest;

import java.util.function.BiConsumer;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-07-20 20:11
 * *
 * @className: TokenConsumer
 * *
 * *
 ******************************************************/
public interface TokenConsumer extends BiConsumer<Document, DocumentRequest>{
    
}
