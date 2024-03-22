
package com.jordilaforge.thisorthatserver.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ScoreResponse {
    private List<Score> scores;
    private Boolean surveyIsRunning;
    private String perspective;
    private Integer numberOfVotes;
    private Integer numberOfUsers;
}
