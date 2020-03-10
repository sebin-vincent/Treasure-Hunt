package com.litmus7.treasure_hunt.dto.requestdto;

import javax.validation.constraints.NotEmpty;

public class CreateContestDto {

    @NotEmpty(message = "Contest name can't be empty")
    private String contestName;

    private boolean active;

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
