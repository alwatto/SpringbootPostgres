package com.drf.Springbootpostgres.dao;

import com.drf.Springbootpostgres.model.document;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

import java.util.*;

@Repository("fakeDao")
public class FakeDocumentDataAccessService implements DocumentDAO {

    private static List<document> DB = new ArrayList<>();

    @Override
    public int insertDocument(document document){
        DB.add(new document(document.getId(), document.getName()));
        return 1;
    }

    @Override
    public List<document> selectAllDocuments() {
        return DB;
    }

    @Override
    public Optional<document> selectDocumentById(String id) {
        return DB.stream()
                .filter(document -> document.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteDocument(String id) {
        Optional<document> DocumentMaybe = selectDocumentById(id);
        if (DocumentMaybe.isEmpty()) {
            return 0;
        }
        DB.remove(DocumentMaybe.get());
        return 1;
    }

    @Override
    public int updateDocument(String id, document update) {
        return selectDocumentById(id)
                .map(document -> {
                    int indexOfDocumentToUpdate = DB.indexOf(document);
                    if (indexOfDocumentToUpdate >= 0){
                        DB.set(indexOfDocumentToUpdate, new document(id, update.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
