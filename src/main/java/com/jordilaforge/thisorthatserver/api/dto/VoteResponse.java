
package com.jordilaforge.thisorthatserver.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VoteResponse {
    private String id1;
    private String id2;
    private Boolean surveyIsRunning;
    private String perspective;
}
