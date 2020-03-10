package com.litmus7.treasure_hunt.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contest")
public class Contest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTEST_ID")
    private int contestId;

    @Column(name = "CONTEST_NAME")
    private String contestName;

    @Column(name = "ENABLED")
    private boolean isEnabled;


    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
