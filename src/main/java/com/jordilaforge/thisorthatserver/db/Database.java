package com.jordilaforge.thisorthatserver.db;

import com.jordilaforge.thisorthatserver.dto.Image;
import com.jordilaforge.thisorthatserver.dto.ScoreItem;
import com.jordilaforge.thisorthatserver.dto.Survey;
import com.jordilaforge.thisorthatserver.dto.Vote;

import java.util.Date;
import java.util.List;

public interface Database {

    long surveyCount();

    void addSurvey(Survey survey);

    void removeSurvey(String code);

    Survey getSurvey(String code);

    String addImageToSurvey(String surveyCode, Image image);

    void startSurvey(String surveyCode);

    void stopSurvey(String surveyCode);

    Image getImageFromSurvey(String surveyCode, String imageId);

    List<Survey> getSurveysOlderThan(Date cutOffDate);

    void addScore(String surveyCode, ScoreItem scoreItem);

    void persistVote(String surveyCode, Vote vote);
}
