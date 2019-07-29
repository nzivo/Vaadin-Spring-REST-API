package com.example.demograd.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Setter
@Getter
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "address")
    private String address;

    public User(){ }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public User( String username, String address){
        this.setUsername(username);
        this.setAddress(address);
    }

    @Override
    public String toString(){
        return "User{" +
                "id=" + id +
                ",username = '" + username + '\'' +
                ",address = '" + address + '\'' +
                '}';
    }
}
