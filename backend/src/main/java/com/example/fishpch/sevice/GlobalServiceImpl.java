package com.example.fishpch.sevice;

import com.example.fishpch.model.Entry;
import com.example.fishpch.model.User;
import com.example.fishpch.repo.EntryRepo;
import com.example.fishpch.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GlobalServiceImpl implements GlobalService {

    private EntryRepo entryRepo;
    private UserRepo userRepo;

    @Override
    public void saveUser(User user) {

        if(user.getFriends() == null) user.setFriends(new ArrayList<>());

        if(user.getEntries() == null) user.setEntries(new ArrayList<>());

        userRepo.save(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepo.deleteById(id);
    }


    @Override
    public void updateUser(User user) {
        userRepo.save(user);
    }

    @Override
    public User getUserById(String id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User getUserByName(String name) {
        return userRepo.findByName(name).orElse(null);
    }

    @Override
    public void saveEntry(Entry entry) {
        User user = userRepo.findById(entry.getUserId()).orElse(null);

        if(user == null) return;

        Entry nEntry =  entryRepo.findById(entry.getId()).orElse(null);

        if(nEntry != null) return;

        user.addEntry(entry.getId());

        entryRepo.save(entry);
        userRepo.save(user);
    }

    @Override
    public void deleteEntry(String id) {

        Entry entry = entryRepo.findById(id).orElse(null);

        if(entry == null) return;

        User user = userRepo.findById(entry.getUserId()).orElse(null);

        if(user == null) return;

        user.remEntry(id);

        userRepo.save(user);
        entryRepo.deleteById(id);
    }

    @Override
    public void updateEntry(Entry entry) {

        entryRepo.save(entry);
    }

    @Override
    public List<Entry> getEntryByUser(String id) {
        User user = userRepo.findById(id).orElse(null);

        if(user == null) return null;
        List<Entry> entryList = new ArrayList<>();

        for (String entry : user.getEntries()) {

            if(entry == null) continue;

            Entry entry1 = entryRepo.findById(entry).orElse(null);

            if(entry1 == null) continue;

            entryList.add(entry1);
        }

        return entryList;
    }

    @Override
    public List<Entry> getEntryAll() {
        return entryRepo.findAll();
    }

    @Override
    public Entry getEntryById(String id) {
        return entryRepo.findById(id).orElse(null);
    }

    @Override
    public Entry getEntryByWeight(String weight) {
        return null;
    }

    @Override
    public Entry getEntryByLength(String length) {
        return null;
    }

    @Override
    public Entry getEntryByDate(String date) {
        return null;
    }

    @Override
    public Entry getEntryByLocation(String location) {
        return null;
    }

    @Override
    public Entry getEntryBySpecies(String species) {
        return null;
    }

    @Override
    public List<User> getUserAll() {
        return userRepo.findAll();
    }

    @Override
    public List<User> getFriendsByUser(String id) {
        User user = userRepo.findById(id).orElse(null);

        if(user == null) return null;

        List<User> friendList = new ArrayList<>();

        for (String friend : user.getFriends()) {

            if(friend == null) continue;

            User user1 = userRepo.findById(friend).orElse(null);

            if(user1 == null) continue;

            friendList.add(user1);
        }

        return friendList;
    }

    @Override
    public String login(String username, String password) {
        User user = userRepo.findByName(username).orElse(null);

        if(user == null) return null;

        if(user.getPassword().equals(password)) return user.getId();

        return null;
    }

    @Override
    public User addFriendTouser(String userId, String friendId) {

        User user = userRepo.findById(userId).orElse(null);

        if(user == null) return null;

        User friend = userRepo.findById(userId).orElse(null);

        if(friend == null) return null;

        user.addFriend(friendId);
        friend.addFriend(userId);

        userRepo.save(user);
        userRepo.save(friend);

        return user;
    }
}
