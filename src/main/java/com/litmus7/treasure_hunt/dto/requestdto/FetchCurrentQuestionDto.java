package com.litmus7.treasure_hunt.dto.requestdto;

import javax.validation.constraints.NotNull;

public class FetchCurrentQuestionDto {

    @NotNull(message = "Question Id can't be null")
    private int contestId;

    @NotNull(message = "User id can't be null")
    private int userId;

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
