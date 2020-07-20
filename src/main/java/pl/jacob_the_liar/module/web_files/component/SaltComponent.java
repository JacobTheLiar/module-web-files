package pl.jacob_the_liar.module.web_files.component;


import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import pl.jacob_the_liar.module.web_files.utils.StringSupplier;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-07-20 21:24
 * *
 * @className: SaltComponent
 * *
 * *
 ******************************************************/
@Component
@RequiredArgsConstructor
public class SaltComponent implements StringSupplier{
    
    private final Environment environment;
    
    
    @Override
    public String get(){
        return environment.getProperty("hashids.salt");
    }
}
