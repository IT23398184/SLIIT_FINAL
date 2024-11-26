package com.victorp.model;

import javax.persistence.*;
import java.util.List;

@Entity // Specifies this class is a JPA entity.
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column // Maps the workout name to a database column.
    private String nameWorkout;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_trainer_id")
    @MapsId // Shares the same primary key as Trainer.
    private Trainer trainer;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "workout", cascade = CascadeType.ALL)
    // Defines a one-to-many relationship with WorkoutPersonal.
    private List<WorkoutPersonal> workoutPersonalList;

    @OneToOne(mappedBy = "workout", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // Defines a one-to-one relationship with WorkoutGroup.
    private WorkoutGroup workoutGroup;
    // Default constructor.
    public Workout() {
    }
    // Parameterized constructor for initialization.
    public Workout(String nameWorkout, Trainer trainer) {
        this.nameWorkout = nameWorkout;
        this.trainer = trainer;
    }
    // Getters and setters for all fields.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameWorkout() {
        return nameWorkout;
    }

    public void setNameWorkout(String nameWorkout) {
        this.nameWorkout = nameWorkout;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public List<WorkoutPersonal> getWorkoutPersonalList() {
        return workoutPersonalList;
    }

    public void setWorkoutPersonalList(List<WorkoutPersonal> workoutPersonalList) {
        this.workoutPersonalList = workoutPersonalList;
    }
    // Adds a WorkoutPersonal to the list.
    public void addWorkoutPersonal(WorkoutPersonal workoutPersonal) {
        this.workoutPersonalList.add(workoutPersonal);
    }

    public WorkoutGroup getWorkoutGroup() {
        return workoutGroup;
    }

    public void setWorkoutGroup(WorkoutGroup workoutGroup) {
        this.workoutGroup = workoutGroup;
    }

    @Override // Custom string representation.
    public String toString() {
        return "Workout{"
                + nameWorkout + '\'' +
                '}';
    }
}
