package com.litmus7.treasure_hunt.dto.responsedto;

import java.util.List;

public class QuestionResponse {

    private int questionId;

    private String question;

    private String imageURL;

    private String audioURL;

    private String answer;

    private List<ClueResponse> clues;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
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

    public List<ClueResponse> getClues() {
        return clues;
    }

    public void setClues(List<ClueResponse> clues) {
        this.clues = clues;
    }
}
