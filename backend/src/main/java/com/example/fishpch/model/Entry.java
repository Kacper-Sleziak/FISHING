package com.example.fishpch.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Entry {

    @Id
    private String id;
    private String userId;
    private String weight;
    private String length;
    private String date;
    private String location;
    private String species;
}
