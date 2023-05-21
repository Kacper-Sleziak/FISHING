package com.example.fishpch.controller;


import com.example.fishpch.model.Entry;
import com.example.fishpch.sevice.GlobalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/entry")
@AllArgsConstructor
@CrossOrigin
@RestController
public class EntriesController {

    private GlobalService globalService;

    @GetMapping("/get/{id}")
    public Entry get(@PathVariable String id) {

        return globalService.getEntryById(id);
    }


    @GetMapping("/all")
    public List<Entry> getAll() {

        return globalService.getEntryAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {

        globalService.deleteEntry(id);

        return ResponseEntity.ok("ok");
    }

    @GetMapping("/user/{id}")
    public List<Entry> getEntries(@PathVariable String id) {

        return globalService.getEntryByUser(id);
    }

    @PostMapping("/")
    public Entry addEntry(@RequestBody Entry entry) {

        globalService.saveEntry(entry);
        return entry;
    }

    @PutMapping("/edit")
    public Entry editEntry(@RequestBody Entry entry) {

        if(entry.getId() == null || entry.getId().isEmpty() || entry.getUserId() == null ||
                entry.getUserId().isEmpty()) {
            return null;
        }

        globalService.saveEntry(entry);
        return entry;
    }
}
