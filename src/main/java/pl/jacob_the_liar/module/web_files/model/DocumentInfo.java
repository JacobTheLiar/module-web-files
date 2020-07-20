package pl.jacob_the_liar.module.web_files.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import pl.jacob_the_liar.module.web_files.utils.DocumentId;
import pl.jacob_the_liar.module.web_files.utils.HashIdDocument;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-06-29 17:40
 * *
 * @className: DocumentResult
 * *
 * *
 ******************************************************/
@Getter
@EqualsAndHashCode(callSuper = false)
public class DocumentInfo extends Document{
    
    private final String hashids;
    private final String directLink;
    private final Boolean isPublic;
    
    
    public DocumentInfo(Document document, String salt, String uri){
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
    
        DocumentId documentId = new HashIdDocument(salt);
        this.hashids = documentId.encode(document.getId());
        this.directLink = uri + hashids;
        this.isPublic = document.getTokenHash() == null;
    }
}
