package com.litmus7.treasure_hunt.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "leaderboard")
public class LeaderBoard implements Serializable{

    @Id
    private int participationId;

    @JoinColumn(name = "PARTICIPATION_ID")
    @ManyToOne
    @MapsId
    private Participation participation;


    @Column(name = "LEVEL")
    private int level;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @Column(name = "LAST_MODIFIED")
    private Date lastModified=new Date();

    public int getParticipationId() {
        return participationId;
    }

    public void setParticipationId(int participationId) {
        this.participationId = participationId;
    }

    public Participation getParticipation() {
        return participation;
    }

    public void setParticipation(Participation participation) {
        this.participation = participation;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
