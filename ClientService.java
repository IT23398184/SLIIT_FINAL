package com.victorp.service;

import com.victorp.model.Client;  // Importing the Client model class
import org.springframework.stereotype.Service;

import java.util.List; // Importing List class for handling collections of Client objects

public interface ClientService {

    List<Client> findClientByKeyword(String keyword) throws Exception;
    List<Client> getAllClient() throws Exception;
    List<Client> getAllClientInGroup(String nameWorkout) throws Exception;
}
