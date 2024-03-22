
package com.jordilaforge.thisorthatserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Survey {
    private Date creationDate;
    private String id;
    private List<Image> images;
    private Boolean started;
    private List<ScoreItem> scores;
    private List<Vote> votes;
    private String perspective;
}
