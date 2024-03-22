
package com.jordilaforge.thisorthatserver.job;

import com.jordilaforge.thisorthatserver.db.InMemoryDatabase;
import com.jordilaforge.thisorthatserver.dto.Survey;
import com.jordilaforge.thisorthatserver.exception.DatabaseException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Log4j2
@Component
public class CleanupJob implements Runnable {

    @Autowired
    private InMemoryDatabase inMemoryDatabase;

    @Scheduled(fixedDelay = 3600000, initialDelay = 3600000)
    public void run() {
        log.info("ENTRY CleanupJob RUN()");
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DAY_OF_MONTH, -1);
        List<Survey> oldSurveys = inMemoryDatabase.getSurveysOlderThan(yesterday.getTime());
        log.info("Removing {} surveys", oldSurveys.size());
        for (Survey survey : oldSurveys) {
            try {
                inMemoryDatabase.removeSurvey(survey.getId());
            } catch (DatabaseException e) {
                log.error("Error when trying to remove survey with code {}. Message {}", survey.getId(), e.getMessage());
            }
        }
        log.info("EXIT CleanupJob RUN()");
    }

}
