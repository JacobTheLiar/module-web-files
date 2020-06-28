package pl.jacob_the_liar.module.web_files.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.jacob_the_liar.module.web_files.model.Document;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2020-06-28 18:19
 * *
 * @className: DocumentRepository
 * *
 * *
 ******************************************************/
public interface DocumentRepository extends JpaRepository<Document, Long>{

}
