package com.jmcaldera.cleanfootball.domain.exception;

/**
 * Created by jmcaldera on 17/08/2017.
 * * Interface to represent a wrapper around an {@link java.lang.Exception} to manage errors.
 */

public interface ErrorBundle {
    Exception getException();

    String getErrorMessage();
}
