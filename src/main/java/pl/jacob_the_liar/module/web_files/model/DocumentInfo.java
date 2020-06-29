package pl.jacob_the_liar.module.web_files.model;


import lombok.Getter;
import org.hashids.Hashids;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-06-29 17:40
 * *
 * @className: DocumentResult
 * *
 * *
 ******************************************************/
@Getter
public class DocumentInfo extends Document{
    
    private final String hashids;
    private final String directLink;
    
    
    public DocumentInfo(Document document, String hashidsSalt, String uri){
        super();
        super.setId(document.getId());
        super.setOriginalName(document.getOriginalName());
        super.setLocalName(document.getLocalName());
        super.setLocalPath(document.getLocalPath());
        super.setContentType(document.getContentType());
        super.setStoreTime(document.getStoreTime());
        super.setHash(document.getHash());
        super.setLastUse(document.getLastUse());
        super.setDeleted(document.getDeleted());
        super.setOwnerIp(document.getOwnerIp());
        
        Hashids ids = new Hashids(hashidsSalt);
        hashids = ids.encode(document.getId());
        
        this.directLink = uri + hashids;
    }
}
