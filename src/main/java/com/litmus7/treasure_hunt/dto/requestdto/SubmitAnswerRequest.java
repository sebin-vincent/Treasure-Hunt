package com.litmus7.treasure_hunt.dto.requestdto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SubmitAnswerRequest {

    @NotNull(message = "ContestId can't be null")
    private int contestId;

    @NotEmpty(message = "Answer can't be null")
    private String answer;

    @NotNull(message = "UserId can't be null")
    private int userId;

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
