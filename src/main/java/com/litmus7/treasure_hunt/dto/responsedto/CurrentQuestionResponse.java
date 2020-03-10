package com.litmus7.treasure_hunt.dto.responsedto;

import java.util.List;

public class CurrentQuestionResponse {

    private int qustionId;

    private String question;

    private String imageURL;

    private String audioURL;

    private List<ClueResponse> clueResponses;

    public int getQustionId() {
        return qustionId;
    }

    public void setQustionId(int qustionId) {
        this.qustionId = qustionId;
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

    public List<ClueResponse> getClueResponses() {
        return clueResponses;
    }

    public void setClueResponses(List<ClueResponse> clueResponses) {
        this.clueResponses = clueResponses;
    }
}
