package pl.jacob_the_liar.module.web_files.service;


import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.jacob_the_liar.module.web_files.model.Document;
import pl.jacob_the_liar.module.web_files.repository.DocumentRepository;
import pl.jacob_the_liar.module.web_files.utils.FileChecksum;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-06-28 18:27
 * *
 * @className: DocumentService
 * *
 * *
 ******************************************************/
@Service
@RequiredArgsConstructor
public class DocumentService{
    
    private final DocumentRepository documentRepository;
    
    @Value("${storage.path}")
    private String localPath;
    @Value("${hashids.salt}")
    private String hashidsSalt;
    
    
    public Document storeDocument(MultipartFile file, String remoteAddress){
        
        Document document = new Document();
        
        document.setOriginalName(file.getOriginalFilename());
        document.setContentType(file.getContentType());
        document.setLocalPath(localPath);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String localFileName = LocalDateTime.now().format(formatter) + "." + FilenameUtils.getExtension(
                file.getOriginalFilename());
        document.setLocalName(localFileName);
        document.setOwnerIp(remoteAddress);
        document.setStoreTime(LocalDateTime.now());
        
        Path fileNameAndPath = Paths.get(localPath, localFileName);
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        documentRepository.save(document);
        
        Hashids hashids = new Hashids(hashidsSalt);
        document.setHashId(hashids.encode(document.getId()));
        
        FileChecksum checksum = new FileChecksum(localPath + localFileName);
        try {
            document.setHash(checksum.getChecksum());
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        documentRepository.save(document);
        
        return document;
    }
}
