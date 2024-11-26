package com.victorp.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity// Marks this class as a JPA entity

@Table(name = "user_role")
public class UserRole implements GrantedAuthority {
    @Id// Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column // Maps this field to a database column
    private String name;

    @Column// Maps this field to a database column
    private Boolean admin;// Indicates if this role has admin privileges

    @Column
    private Boolean trainer;

    @ManyToMany(mappedBy = "userRole", fetch = FetchType.EAGER)
    // Establishes a bidirectional Many-to-Many relationship with the User entity
    private Set<User> users;

      // Default constructor

    public UserRole() {
    }
 // Constructor with parameters
    public UserRole(String name, Boolean admin, Boolean trainer) {
        this.name = name;
        this.admin = admin;
        this.trainer = trainer;
    }
 // Getter for id
    public Long getId() {

        return id;
    }
  // Setter for id
    public void setId(Long id) {
        this.id = id;
    }
  // Getter for name
    public String getName() {

        return name;
    }
// Setter for name
    public void setName(String name) {
        this.name = name;
    }
// Getter for admin
    public Boolean getAdmin() {

        return admin;
    }
// Setter for admin
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
// Setter for trainer
    public void setTrainer(Boolean trainer) {

        this.trainer = trainer;
    }
// Getter for trainer
    public Boolean getTrainer() {

        return trainer;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {

        this.users.add(user);
    }

    @Override
    public String getAuthority() {
        return name;// Returns the role name as the authority
    }
}
