package com.drf.Springbootpostgres.dao;

import com.drf.Springbootpostgres.model.document;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.Optional;

public interface DocumentDAO {


    int insertDocument(document document);
    //default int insertDocument(document document){
        //UUID id = UUID.randomUUID();
        //return insertDocument(id, document);
    //}

    List<document> selectAllDocuments();

    Optional<document> selectDocumentById(String id);

    int deleteDocument(String id);

    int updateDocument(String id, document document);

}
