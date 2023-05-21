package com.example.fishpch.repo;

import com.example.fishpch.model.Entry;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepo extends MongoRepository<Entry, String> {

}
