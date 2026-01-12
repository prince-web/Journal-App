package com.Kanha.Journal.App.service;

import com.Kanha.Journal.App.entity.JournalEntry;
import com.Kanha.Journal.App.entity.UserEntry;
import com.Kanha.Journal.App.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
//        journalEntryRepository.save(journalEntry);

        try {
            UserEntry user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);

    }


    public List<JournalEntry> getAll(){
        return  journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
boolean removed =false;
        try {
            UserEntry user = userService.findByUserName(userName);//implement cascade delete
             removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));//implement cascade delete
            if(removed){
                userService.saveEntry(user);//implement cascade delete
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occured while deleting the entry: "+e);
        }
        return removed;

    }


}
