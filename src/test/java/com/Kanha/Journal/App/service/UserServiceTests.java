//package com.Kanha.Journal.App.service;
//
//import com.Kanha.Journal.App.entity.UserEntry;
//import com.Kanha.Journal.App.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class UserServiceTests {
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    UserService userService;
//
//    @Test
//    public void testFindByUserName(){
//        assertEquals(4,2+2);
//        assertNotNull(userRepository.findByUserName("Prince1"));
//    }
//
////    @Test
////    public void testTheUserHasEntry(){
////        UserEntry user = userRepository.findByUserName("saloni");
////        assertTrue(!user.getJournalEntries().isEmpty());
////
////    }
//
//    @ParameterizedTest
//    @ArgumentsSource(UserArgumentProvider.class)
//    public void testSaveNewUser(UserEntry user){
//        assertTrue(userService.saveNewUser(user));
//    }
//}
