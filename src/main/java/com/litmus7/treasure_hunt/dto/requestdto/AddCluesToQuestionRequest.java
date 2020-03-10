package com.litmus7.treasure_hunt.dto.requestdto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class AddCluesToQuestionRequest {

    @NotNull(message = "Question can't be null")
    private int questionId;

    @NotNull(message = "Clue field can't be null")
    private List<CreateClue> clues;


    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public List<CreateClue> getClues() {
        return clues;
    }

    public void setClues(List<CreateClue> clues) {
        this.clues = clues;
    }
}
