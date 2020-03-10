package com.litmus7.treasure_hunt.dto.requestdto;

import javax.validation.constraints.NotNull;

public class ChangeContestStatus {

    @NotNull(message = "status field can't be null")
    private int contestId;

    @NotNull(message = "status field can't be null")
    private boolean contestStatus;

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public boolean isContestStatus() {
        return contestStatus;
    }

    public void setContestStatus(boolean contestStatus) {
        this.contestStatus = contestStatus;
    }
}
