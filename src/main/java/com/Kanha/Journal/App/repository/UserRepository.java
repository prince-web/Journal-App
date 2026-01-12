package com.Kanha.Journal.App.repository;

import com.Kanha.Journal.App.entity.JournalEntry;
import com.Kanha.Journal.App.entity.UserEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntry, ObjectId> {
    UserEntry findByUserName(String username);

    void deleteByUserName(String name);
}
