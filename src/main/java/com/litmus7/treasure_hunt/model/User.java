package com.litmus7.treasure_hunt.model;

import jdk.jfr.Enabled;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_master")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USERID")
    private int userId;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NAME")
    private String Name;

    @Column(name = "ENABLED")
    private boolean Enabled;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public boolean isEnabled() {
        return Enabled;
    }

    public void setEnabled(boolean enabled) {
        Enabled = enabled;
    }
}
