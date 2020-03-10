package com.litmus7.treasure_hunt.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question_master")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUESTION_ID")
    private int questionId;

    @Column(name = "CONTEST_ID")
    private int contestId;

    @Column(name = "QUESTION")
    private String question;

    @Column(name = "IMAGE_URL")
    private String imageURL;

    @Column(name = "AUDIO_URL")
    private String audioURL;

    @Column(name = "ANSWER")
    private String answer;

    @OneToMany(cascade ={ CascadeType.ALL},fetch = FetchType.LAZY, mappedBy = "question")
    private List<Clue> clues = new ArrayList<>();

    @Column(name = "CURRENT_CLUE")
    private int current_clue;

    @Column(name = "ACTIVE")
    private boolean active;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<Clue> getClues() {
        return clues;
    }

    public void setClues(List<Clue> clues) {
        this.clues = clues;
    }

    public int getCurrent_clue() {
        return current_clue;
    }

    public void setCurrent_clue(int current_clue) {
        this.current_clue = current_clue;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
