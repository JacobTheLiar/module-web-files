package pl.jacob_the_liar.module.web_files;


import lombok.RequiredArgsConstructor;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-06-27 10:08
 * *
 * @className: GetFileController
 * *
 * *
 ******************************************************/
@RequiredArgsConstructor
@RestController
public class GetFileController{
    
    @Value("${hashids.salt}")
    private String hashidsSalt;
    
    
    @GetMapping("{fileId}")
    public String getFile(@PathVariable String fileId){
        
        Hashids hashids = new Hashids(hashidsSalt);
        
        return hashids.decode(fileId).toString();
    }
}
