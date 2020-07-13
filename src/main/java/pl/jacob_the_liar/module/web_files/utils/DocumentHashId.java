package pl.jacob_the_liar.module.web_files.utils;


import org.hashids.Hashids;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-07-09 19:00
 * *
 * @className: HashId
 * *
 * *
 ******************************************************/
public class DocumentHashId implements DocumentId{
    
    private final Hashids hashids;
    
    
    public DocumentHashId(String salt){
        this.hashids = new Hashids(salt);
    }
    
    
    public String encode(long id){
        return hashids.encode(id);
    }
    
    
    public long decode(String hashId){
        long[] decode = hashids.decode(hashId);
        return decode[0];
    }
}
