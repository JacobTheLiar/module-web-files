package pl.jacob_the_liar.module.web_files.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.jacob_the_liar.module.web_files.utils.DocumentChecksumInfo;
import pl.jacob_the_liar.module.web_files.utils.DocumentStore;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-06-27 08:34
 * *
 * @className: Store
 * *
 * *
 ******************************************************/

@Entity
@Table(name = "document", schema = "public")
@Data
@NoArgsConstructor
public class Document implements DocumentStore, DocumentChecksumInfo{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    @JsonIgnore
    private Long id;
    private String originalName;
    @JsonIgnore
    private String localName;
    @JsonIgnore
    private String localPath;
    private String contentType;
    private LocalDateTime storeTime;
    private String hash;
    private LocalDateTime lastUse;
    private LocalDateTime deleted;
    @JsonIgnore
    private String ownerIp;
    @JsonIgnore
    private String tokenHash;
}
