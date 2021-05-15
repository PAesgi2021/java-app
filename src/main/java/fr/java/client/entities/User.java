package fr.java.client.entities;


import fr.java.client.utils.types.Roles;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {

    String username;
    String password;
    String firstname;
    String lastname;
    LocalDate dob;
    Roles  roles;

    public User(String username, String password, String firstname, String lastname, Roles roles) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.roles = roles;
    }

    public User(String username, String password, String firstname, String lastname, LocalDate dob) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.roles = Roles.Client;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Roles getRoles() {
        return roles;
    }
}
