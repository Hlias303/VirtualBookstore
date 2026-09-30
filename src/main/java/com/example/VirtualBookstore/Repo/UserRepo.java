package com.example.VirtualBookstore.Repo;

import com.example.VirtualBookstore.Model.User;
import com.example.VirtualBookstore.Model.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    User findByUsername(String username);

//    @Query("select u from User u where name = ?1")
    boolean existsByusername(String username);

}
