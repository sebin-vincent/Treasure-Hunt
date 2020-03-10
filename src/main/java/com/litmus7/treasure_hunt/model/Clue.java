package com.litmus7.treasure_hunt.model;

import javax.persistence.*;

@Entity
@Table(name = "clue_master")
public class Clue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLUE_ID")
    private int clueId;

    @Column(name = "CLUE_DESCRIPTION")
    private String clueDescription;

    @Column(name = "IMAGE_URL")
    private String imageURL;

    @Column(name = "AUDIO_URL")
    private String audioURL;

    @Column(name = "ACTIVE")
    private boolean active;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "QUESTION_ID", nullable = true)
    private Question question;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getClueId() {
        return clueId;
    }

    public void setClueId(int clueId) {
        this.clueId = clueId;
    }

    public String getClueDescription() {
        return clueDescription;
    }

    public void setClueDescription(String clueDescription) {
        this.clueDescription = clueDescription;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getAudioURL() {
        return audioURL;
    }

    public void setAudioURL(String audioURL) {
        this.audioURL = audioURL;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
