package pl.jacob_the_liar.module.web_files.utils;


import pl.jacob_the_liar.module.web_files.model.Document;
import pl.jacob_the_liar.module.web_files.utils.consumer.TokenConsumer;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Optional;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-07-20 20:13
 * *
 * @className: TokenDocument
 * *
 * *
 ******************************************************/
public class TokenDocument implements TokenConsumer{
    
    @Override
    public void accept(Document document, DocumentRequest documentRequest){
        Optional<String> tokenOptional = Optional.ofNullable(documentRequest.getToken());
        tokenOptional.ifPresent(s -> document.setTokenHash(getMD5(s)));
    }
    
    
    private String getMD5(String token){
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(token.getBytes(StandardCharsets.UTF_8));
            return BytesToHex.convert(hash);
        } catch (Exception ex) {
            // todo implement exception
            ex.printStackTrace();
        }
        return null;
    }
}
