package com.victorp.repository;


import com.victorp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component// Marks this as a Spring-managed component for dependency injection
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username = :username")
    User findByUsername(@Param("username") String username);

   
    @Query(value = "select * from User u where u.username like %:keyword% or u.last_name like %:keyword%", nativeQuery = true)
    // Native SQL query to search for users by username or last name containing a specific keyword
    List<User> findUserByKeyword(@Param("keyword") String keyword);


}
