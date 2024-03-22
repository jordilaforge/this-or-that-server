
package com.jordilaforge.thisorthatserver.vote;

import com.jordilaforge.thisorthatserver.dto.ScoreItem;
import com.jordilaforge.thisorthatserver.dto.VoteItem;

import java.util.List;

public interface VoteAlgorithm {

    void initialize(String surveyCode);

    VoteItem getVote(String surveyCode, String userId);

    void setVote(String surveyCode, String imageIdWinner, String imageIdLooser, String userId);

    List<ScoreItem> calculateScores(String surveyCode);
}
