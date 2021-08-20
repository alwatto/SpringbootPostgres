package com.drf.Springbootpostgres.dao;

import com.drf.Springbootpostgres.model.document;
import org.elasticsearch.common.Nullable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("postgres")
public class postgres implements DocumentDAO{

    private final JdbcTemplate jdbcTemplate;

    public postgres(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertDocument(document document) {
        final String sql = "INSERT INTO document (id, name) VALUES (?, ?)";
        //document newDocument = new document(document.getId().toString(),document.getName().toString());
        jdbcTemplate.update(sql, document.getId(), document.getName());
        //jdbcTemplate.update(sql);
        return 1;
        //document newDocument =
        //jdbcTemplate.update(sql, new Object[]{document});
    }

    @Override
    public List<document> selectAllDocuments() {
        final String sql = "SELECT id, name FROM document";
        List<document> documents = jdbcTemplate.query(sql, (resultSet, i) -> {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            return new document(id, name);
        });
        return documents;
        //return null;
    }

    @Override
    public Optional<document> selectDocumentById(String id) {
        final String sql = "SELECT id, name FROM document WHERE id = ?";
        document documented = jdbcTemplate.queryForObject(sql, new Object[]{id},  (resultSet, i) -> {
            String documentid = resultSet.getString("id");
            String name = resultSet.getString("name");
            return new document(documentid, name);
        });
        return Optional.ofNullable(documented);
    }

    @Override
    public int deleteDocument(String id) {
        Optional<document> DocumentMaybe = selectDocumentById(id);
        if (DocumentMaybe.isEmpty()) {
            return 0;
        }
        final String sql = "DELETE FROM document WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return 1;

    }

    @Override
    public int updateDocument(String id, document update) {
        final String sql = "UPDATE document SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, update.getName(), update.getId());
        return 1;
    }
}
