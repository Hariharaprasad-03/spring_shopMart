package com.example.spring_jpa.model;
import jakarta.persistence.*;

@Entity
@Table( name = "password_table")
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    @Column(name = "passwordId")
    private int id;

    @Column( name = "password")
    private String password;

    public Password() {
    }

    public Password(int id ,String password) {
        this.id = id;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
