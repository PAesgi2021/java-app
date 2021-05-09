package fr.java.client.entities;


import fr.java.client.utils.types.Roles;

public class User {

    String username;
    String password;
    String alias;
    int    age;
    Roles  roles;

    public User(String username, String password, String alias, int age, Roles roles) {
        this.username = username;
        this.password = password;
        this.alias = alias;
        this.age = age;
        this.roles = roles;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
