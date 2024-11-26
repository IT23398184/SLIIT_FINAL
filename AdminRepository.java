package com.victorp.repository;

//Repository interface for the Admin entity.
 // Provides methods to interact with the database using Spring Data JPA.
 //Inherits standard CRUD operations from JpaRepository and includes custom query methods.

import com.victorp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdminRepository extends JpaRepository<Admin, Long> {

//Custom JPQL query to find an Admin entity by its username.
    @Query("select a from Admin a where a.username = :username")
    Admin findByUsername(@Param("username") String username);

    @Query(value = "select * from Admin a where a.username like %:keyword%", nativeQuery = true)
    List<Admin> findAdminByKeyword(@Param("keyword") String keyword);
}
