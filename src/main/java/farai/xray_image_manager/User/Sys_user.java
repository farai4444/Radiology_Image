package farai.xray_image_manager.User;

import jakarta.persistence.*;

import java.io.Serializable;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "sys_user")
public class Sys_user implements Serializable {
    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = IDENTITY)
    private int userId;
    @Column(name = "name")
    private String name;
    @Column(name= "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name="password")
    private String password;

    public String getACCESS_PRIVILEGES() {
        return ACCESS_PRIVILEGES;
    }

    public void setACCESS_PRIVILEGES(String ACCESS_PRIVILEGES) {
        this.ACCESS_PRIVILEGES = ACCESS_PRIVILEGES;
    }

    @Column(name = "ACCESS_PRIVILEGES")
    private String ACCESS_PRIVILEGES;
    public Sys_user(){}

    public Sys_user(String name, String surname, String email, String username, String password,String ACCESS_PRIVILEGES) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.ACCESS_PRIVILEGES = ACCESS_PRIVILEGES;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
