package com.example.fishpch.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;

    private List<String> entries;
    private List<String> friends;
    public void addEntry(String entryId){
        entries.add(entryId);
    }

    public void remEntry(String entryId){
        entries.remove(entryId);
    }
    public void addFriend(String friendId){
        friends.add(friendId);
    }

    public void remFriend(String friendId){
        friends.remove(friendId);
    }

    private boolean admin;
}
