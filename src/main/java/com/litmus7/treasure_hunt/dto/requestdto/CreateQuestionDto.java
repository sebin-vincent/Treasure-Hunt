package com.litmus7.treasure_hunt.dto.requestdto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CreateQuestionDto {

    @NotNull(message = "ContestId can't be empty")
    private int contestId;

    private String question;

    private String imageURL;

    private String audioURL;

    private String answer;

    private List<CreateClue> clues;

    private int currentClue;

    private boolean active;

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

    public List<CreateClue> getClues() {
        return clues;
    }

    public void setClues(List<CreateClue> clues) {
        this.clues = clues;
    }

    public int getCurrentClue() {
        return currentClue;
    }

    public void setCurrentClue(int currentClue) {
        this.currentClue = currentClue;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
