package ru.kpfu.webproject.fayzrakhmanov.entity;

public class User {

    private String name;
    private String login;
    private String passwordHash;
    private String email;

    public User(String name, String login, String passwordHash, String email) {
        this.name = name;
        this.login = login;
        this.passwordHash = passwordHash;
        this.email = email;
    }

    public User() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
