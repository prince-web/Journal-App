package com.Kanha.Journal.App.controller;

import com.Kanha.Journal.App.entity.JournalEntry;
import com.Kanha.Journal.App.entity.UserEntry;
import com.Kanha.Journal.App.service.JournalEntryService;
import com.Kanha.Journal.App.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;



//    @GetMapping
//    public ResponseEntity<?> getAll() {
//        List<JournalEntry> all = journalEntryService.getAll();
//        if (all!=null && !all.isEmpty()){
//            return  new ResponseEntity<>(all,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
@GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser() {
    System.out.println("We are inside the "+"getAllJournalEntriesOfUser()");
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();
    System.out.println("the value is: "+authentication.getName());
    UserEntry user  = userService.findByUserName(userName);
    List<JournalEntry> all = user.getJournalEntries();
        if (all!=null && !all.isEmpty()){
            return  new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
        System.out.println("We are inside the "+"CreateEntry! ");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        try {
            journalEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    //    localhost:8080/journal/id/2+
    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
        System.out.println("We are inside the "+"getJournalEntryById()");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntry user = userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
            if (journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {
        System.out.println("We are inside the "+"deleteJournalEntryById()");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean removed = journalEntryService.deleteById(myId,userName);

        if(removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId id,
                                               @RequestBody JournalEntry newEntry) {
        System.out.println("We are inside the "+"updateJournalById()");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntry user = userService.findByUserName(userName);
        List<JournalEntry>collect = user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
            if (journalEntry.isPresent()){
                JournalEntry old = journalEntry.get();
                old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent()!=null && !newEntry.getTitle().equals("")? newEntry.getContent() : old.getContent());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }


       return new ResponseEntity<>(HttpStatus.NOT_FOUND);

//        return null;
    }
}
