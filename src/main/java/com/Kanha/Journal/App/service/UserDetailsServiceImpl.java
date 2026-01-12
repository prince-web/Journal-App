package com.Kanha.Journal.App.service;

import com.Kanha.Journal.App.entity.UserEntry;
import com.Kanha.Journal.App.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository ;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntry user = userRepository.findByUserName(username);
        if (user!=null){
            UserDetails userDetails = User.builder().
                    username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
            return userDetails;
        }
       throw new UsernameNotFoundException("User not found with the username: " + username);
    }
}
