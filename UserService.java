package com.victorp.service;

import com.victorp.model.Client;
import com.victorp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserService {
    // Interface defining the service layer for User operations
    User getById(Long id) throws Exception;
 // Fetches a User by their unique ID, throws an exception if not found
    List<User> getAll() throws Exception;
// Retrieves a list of all Users, throws an exception if operation fails
    User getByUsername(String username) throws Exception;

    User checkUser(String login);

    User create(User item) throws Exception;

    User update(User item) throws Exception;

    void delete(Long id) throws Exception;
    boolean saveClient(User user) throws Exception;
    boolean saveAdmin(User user) throws Exception;
    boolean saveTrainer(User user) throws Exception;
    List<User> findUserByKeyword(String keyword) throws Exception;

    Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
