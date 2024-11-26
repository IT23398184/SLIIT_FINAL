package com.victorp.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class WorkoutGroup {

//The unique identifier for a WorkoutGroup entity.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Workout workout;

    @Column
    private String nameWorkout;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "workoutGroup", cascade = CascadeType.ALL)
    private List<Client> clientList;
    @Column

    //The time range allocated for the workout session.
    private String trainingTime;

    public WorkoutGroup() {
    }

//Default constructor for creating an empty WorkoutGroup instance.
    public WorkoutGroup(List<Client> clientList, String trainingTime) {

        this.clientList = clientList;
        this.trainingTime = trainingTime;
    }

// Getters and setters for accessing and modifying WorkoutGroup properties.
    public List<Client> getClientList() {

        return clientList;
    }

    public void setClientList(List<Client> clientList) {

        this.clientList = clientList;
    }

// Adds a single client to the group.
    public void setClientToGroup(Client client) {

        this.clientList.add(client);
    }

    public String getTrainingTime() {
        return trainingTime;
    }

    public void setTrainingTime(String trainingTime) {

        this.trainingTime = trainingTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public String getNameWorkout() {
        return nameWorkout;
    }

    public void setNameWorkout(String nameWorkout) {
        this.nameWorkout = nameWorkout;
    }
}

