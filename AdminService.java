package com.victorp.service;



import com.victorp.model.Admin;

import java.util.List;

//Service interface for managing Admin entities.
public interface AdminService {
    //Searches for Admin entities by a keyword in their usernames.
    List<Admin> findAdminByKeyword(String keyword) throws Exception;

//Deletes an Admin entity by its ID.
    void delete(Long id) throws Exception;

//Retrieves an Admin entity by username.
    Admin getByUsername(String username) throws Exception;

//Fetches all Admin entities.
    List<Admin> getAll() throws Exception;
}

