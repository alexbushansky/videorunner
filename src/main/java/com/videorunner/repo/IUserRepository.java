package com.videorunner.repo;

import com.videorunner.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends JpaRepository<User,Long> {

    User findByName(String name);
    User findByEmail(String email);
}
