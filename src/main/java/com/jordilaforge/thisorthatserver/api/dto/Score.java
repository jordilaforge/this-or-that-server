
package com.jordilaforge.thisorthatserver.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class
Score {
    private String imageId;
    private Integer score;
}
