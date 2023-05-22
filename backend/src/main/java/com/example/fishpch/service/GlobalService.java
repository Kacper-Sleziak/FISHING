package com.example.fishpch.service;


import com.example.fishpch.model.Entry;
import com.example.fishpch.model.User;

import java.util.List;

public interface GlobalService {

    void saveUser(User user);
    void deleteUser(String id);
    void updateUser(User user);
    User getUserById(String id);
    User getUserByName(String name);

    void saveEntry(Entry entry);
    void deleteEntry(String id);
    void updateEntry(Entry entry);

    List<Entry> getEntryByUser(String id);
    List<Entry> getEntryAll();
    Entry getEntryById(String id);
    Entry getEntryByWeight(String weight);
    Entry getEntryByLength(String length);
    Entry getEntryByDate(String date);
    Entry getEntryByLocation(String location);
    Entry getEntryBySpecies(String species);

    List<User> getUserAll();
    List<User> getFriendsByUser(String id);

    String login(String username, String password);

    User addFriendTouser(String userId, String friendId);
}
