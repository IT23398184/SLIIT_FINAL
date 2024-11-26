package com.victorp.repository;

import com.victorp.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component// Marks this interface as a Spring-managed component for dependency injection
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query("select ur from UserRole ur where ur.name = :name")
     // JPQL query to find a UserRole by its name
    UserRole findByName(@Param("name") String name);
    // Uses @Param to bind the method parameter "name" to the query
}
