package pl.jacob_the_liar.module.web_files;


import org.hashids.Hashids;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-06-28 18:51
 * *
 * @className: HashidsTests
 * *
 * *
 ******************************************************/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)

public class HashidsTests{
    
    @Value("${hashids.salt}")
    private String hashSalt;
    
    
    @Test
    public void getHashidsLength(){
        
        Hashids hashids = new Hashids(hashSalt);
        long max = 9007199254740992L;
        
        System.out.println("check: " + max);
        System.out.println("salt: " + hashSalt);
        String enc = hashids.encode(max);
        System.out.println("encoded: " + enc);
        long[] dec = hashids.decode(enc);
        
        assertThat(dec[0]).isEqualTo(max);
    }
}
