package com.videorunner.services;

import com.videorunner.annotation.ValidPassword;
import com.videorunner.models.Role;
import com.videorunner.models.User;
import com.videorunner.repo.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class UserService implements UserDetailsService {

    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Not found!");
        }

        return user;
    }


    public User getByName(String name)
    {
        return userRepository.findByName(name);
    }

    public User getByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    public boolean saveUser(User user)
    {

        User newUser =  userRepository.findByEmail(user.getEmail());


        if(newUser != null)
        {
           return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));

        user.setRoles(Collections.singleton(Role.USER));

        userRepository.save(user);

        return true;
    }


}
