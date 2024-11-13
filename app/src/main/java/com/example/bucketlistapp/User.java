package com.example.bucketlistapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.Ignore;

import java.util.Date;

@Entity(tableName = "USER",
    indices = {@Index(value = {"EMAIL"},unique = true)}
)
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private Integer id;
    @ColumnInfo(name = "EMAIL")
    private String email;
    @ColumnInfo(name = "PASSWORD")
    private String password;
    @ColumnInfo(name = "FIRST_NAME")
    private String firstname;
    @ColumnInfo(name = "LAST_NAME")
    private String lastname;
    @ColumnInfo(name = "LAST_UPDATE")
    private Date lastUpdate;

    @Ignore
    public User() {

    }

    public User(String email, String password, String firstname, String lastname, Date lastUpdate) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.lastUpdate = lastUpdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}

