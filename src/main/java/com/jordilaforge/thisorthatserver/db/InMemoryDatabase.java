
package com.jordilaforge.thisorthatserver.db;

import com.jordilaforge.thisorthatserver.dto.Image;
import com.jordilaforge.thisorthatserver.dto.ScoreItem;
import com.jordilaforge.thisorthatserver.dto.Survey;
import com.jordilaforge.thisorthatserver.dto.Vote;
import com.jordilaforge.thisorthatserver.exception.DeleteFailedException;
import com.jordilaforge.thisorthatserver.exception.ImageNotFoundException;
import com.jordilaforge.thisorthatserver.exception.SurveyNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Repository
@Log4j2
public class InMemoryDatabase implements Database {

    private static final CopyOnWriteArrayList<Survey> surveys = new CopyOnWriteArrayList<>();
    private static long createdSurveyCounter = 0L;

    @Override
    public long surveyCount() {
        return createdSurveyCounter;
    }

    @Override
    public void addSurvey(Survey survey) {
        surveys.add(survey);
        log.info("added survey {} at index: {}", survey.getId(), createdSurveyCounter);
        createdSurveyCounter++;
    }

    @Override
    public void removeSurvey(String code) {
        boolean removed = surveys.removeIf(survey -> survey.getId().equals(code));
        if (removed) {
            log.info("survey deleted {}", code);
        } else {
            log.error("survey with {} could not be deleted", code);
            throw new DeleteFailedException("survey " + code + " could not be deleted");
        }
    }

    @Override
    public Survey getSurvey(String code) {
        Optional<Survey> surveyOptional = surveys.stream().filter(survey -> survey.getId().equals(code)).findFirst();
        if (surveyOptional.isPresent()) {
            return surveyOptional.get();
        } else {
            log.error("survey {} not found", code);
            throw new SurveyNotFoundException("survey " + code + " not found");
        }
    }

    @Override
    public String addImageToSurvey(String surveyCode, Image image) {
        Survey survey = getSurvey(surveyCode);
        survey.getImages().add(image);
        String id = Integer.toString(survey.getImages().indexOf(image));
        image.setId(id);
        log.info("added image {} to {}", image.getId(), surveyCode);
        return id;
    }

    @Override
    public void startSurvey(String surveyCode) {
        getSurvey(surveyCode).setStarted(true);
        log.info("started survey {}", surveyCode);
    }

    @Override
    public void stopSurvey(String surveyCode) {
        getSurvey(surveyCode).setStarted(false);
        log.info("closed survey {}", surveyCode);
    }

    @Override
    public Image getImageFromSurvey(String surveyCode, String imageId) {
        Survey survey = getSurvey(surveyCode);
        return survey.getImages().stream()
                .filter(image -> image.getId().equals(imageId))
                .findFirst()
                .orElseThrow(() -> new ImageNotFoundException("survey" + surveyCode + " image " + imageId + "not found "));
    }

    @Override
    public List<Survey> getSurveysOlderThan(Date cutOffDate) {
        return surveys.stream().filter(s -> s.getCreationDate().before(cutOffDate)).collect(Collectors.toList());
    }

    @Override
    public void addScore(String surveyCode, ScoreItem scoreItem) {
        getSurvey(surveyCode).getScores().add(scoreItem);
    }

    @Override
    public void persistVote(String surveyCode, Vote vote) {
        Survey survey = getSurvey(surveyCode);
        survey.getVotes().add(vote);
    }

}
