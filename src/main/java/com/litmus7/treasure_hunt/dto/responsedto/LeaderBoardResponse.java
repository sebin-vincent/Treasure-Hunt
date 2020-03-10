package com.litmus7.treasure_hunt.dto.responsedto;

import java.util.Date;

public class LeaderBoardResponse {

    public LeaderBoardResponse() {
    }


    public LeaderBoardResponse(String name, int level, Date timeCompletd) {
        this.name = name;
        this.level = level;
        this.timeCompletd = timeCompletd;
    }

    private String name;

    private int level;

    private Date timeCompletd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Date getTimeCompletd() {
        return timeCompletd;
    }

    public void setTimeCompletd(Date timeCompletd) {
        this.timeCompletd = timeCompletd;
    }
}
