package model;

public class User {
    private String id, fullname, username, password, position;
    public User() {
    }
    public User(String id, String fullname, String username, String password, String position) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.position = position;
    }

    @Override
    public String toString() {
        return fullname+" "+position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String role) {
        this.position = role;
    }
}