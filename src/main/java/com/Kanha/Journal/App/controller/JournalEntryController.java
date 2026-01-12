package com.Kanha.Journal.App.controller;


import com.Kanha.Journal.App.JournalApplication;
import com.Kanha.Journal.App.entity.JournalEntry;
import jdk.jfr.consumer.RecordedThread;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {

    private Map<String, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
//        journalEntries.put(myEntry.getId(),myEntry);
        return true;
    }


//    localhost:8080/journal/id/2
    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId){
        return  journalEntries.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteJournalEntryById(@PathVariable Long myId){
        return  journalEntries.remove(myId);
    }

    @PutMapping("id/{id}")
    public JournalEntry updateJournalById(@PathVariable String id,@RequestBody JournalEntry newEntry){
       return journalEntries.put(id,newEntry);
    }

}
