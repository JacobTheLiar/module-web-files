package pl.jacob_the_liar.module.web_files.utils;


import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-07-20 20:51
 * *
 * @className: DocumentRequestImpl
 * *
 * *
 ******************************************************/
@RequiredArgsConstructor
public class DocumentRequestImpl implements DocumentRequest{
    
    private final HttpServletRequest request;
    
    
    @Override
    public String getRemoteHost(){
        return request.getRemoteHost();
    }
    
    
    @Override
    public String getToken(){
        return request.getHeader("token");
    }
    
    
    @Override
    public String getRequestURL(){
        return request.getRequestURL().toString();
    }
}
