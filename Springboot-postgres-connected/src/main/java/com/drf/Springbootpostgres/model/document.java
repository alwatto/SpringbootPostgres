package com.drf.Springbootpostgres.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Document(indexName = "alexTest")
public class document {


    private final String id;
    @NotBlank
    private final String name;

    public document(@JsonProperty("id") String id,
                    @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;

    }
    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
