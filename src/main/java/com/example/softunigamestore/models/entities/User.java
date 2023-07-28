package com.example.softunigamestore.models.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

//    Users can register in the system. After successful registration, the user has email, password, full name,
//    list of games and information on whether he/she is an administrator or not.
//    The first registered user becomes also an administrator. You can manually mark users as admins in the database.

    private String email;
    private String password;
    private String fullName;
    private Set<Game> games;
    private boolean isAdmin;

    @ManyToMany
    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public User() {
    }

    @Column
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
