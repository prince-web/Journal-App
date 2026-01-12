package com.Kanha.Journal.App.service;

import com.Kanha.Journal.App.entity.JournalEntry;
import com.Kanha.Journal.App.entity.UserEntry;
import com.Kanha.Journal.App.repository.JournalEntryRepository;
import com.Kanha.Journal.App.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveEntry(UserEntry userEntry){
        try {
//            userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
            userEntry.setRoles(Arrays.asList("USER"));
            userRepository.save(userEntry);
        } catch (Exception e) {
//            System.out.println("error is occured: "+e);
            log.error("Error occured for {}",userEntry.getUserName(),e);
        }
    }

    public boolean saveNewUser(UserEntry userEntry){
        try {
            userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
            userEntry.setRoles(Arrays.asList("USER"));
            userRepository.save(userEntry);
            return true;
        } catch (Exception e) {
            System.out.println("error is occured: "+e);
            return false;

        }
    }

    public List<UserEntry> getAll(){
        return  userRepository.findAll();
    }

    public Optional<UserEntry> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }
    public UserEntry findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public void saveAdmin(UserEntry userEntry) {
        try {
            userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
            userEntry.setRoles(Arrays.asList("USER","ADMIN"));
            userRepository.save(userEntry);
        } catch (Exception e) {
            System.out.println("error is occured: "+e);

        }
    }
}
