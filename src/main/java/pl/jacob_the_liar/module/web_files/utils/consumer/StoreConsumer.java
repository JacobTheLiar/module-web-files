package pl.jacob_the_liar.module.web_files.utils.consumer;


import pl.jacob_the_liar.module.web_files.utils.DocumentBytes;
import pl.jacob_the_liar.module.web_files.utils.DocumentStore;

import java.util.function.BiConsumer;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-07-13 21:00
 * *
 * @className: StoreConsumer
 * *
 * *
 ******************************************************/
public interface StoreConsumer extends BiConsumer<DocumentStore, DocumentBytes>{
    
}
