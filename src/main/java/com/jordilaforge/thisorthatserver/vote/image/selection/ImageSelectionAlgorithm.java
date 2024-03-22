
package com.jordilaforge.thisorthatserver.vote.image.selection;

import com.jordilaforge.thisorthatserver.dto.Image;
import com.jordilaforge.thisorthatserver.dto.Pair;

public interface ImageSelectionAlgorithm {
    Pair<Image> getNextImagePair(String surveyCode, String userId);
}
