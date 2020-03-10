package com.litmus7.treasure_hunt.dto.responsedto;

public class SubmitAnswerResponse {

    public SubmitAnswerResponse() {
    }

    public SubmitAnswerResponse(boolean correctAnswer, String message) {
        this.correctAnswer = correctAnswer;
        this.message = message;
    }

    private boolean correctAnswer;

    private String message;

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
