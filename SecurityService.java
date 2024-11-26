package com.victorp.service;

//Service interface for security-related operations.
public interface SecurityService {

    //Retrieves the username of the currently logged-in user.
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
