
package com.jordilaforge.thisorthatserver.exception;

public class SurveyAlreadyStartedException extends RuntimeException {
    public SurveyAlreadyStartedException(String message) {
        super(message);
    }
}
