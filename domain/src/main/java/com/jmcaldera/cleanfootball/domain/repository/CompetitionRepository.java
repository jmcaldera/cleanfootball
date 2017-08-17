package com.jmcaldera.cleanfootball.domain.repository;

import com.jmcaldera.cleanfootball.domain.exception.ErrorBundle;
import com.jmcaldera.cleanfootball.domain.model.Competition;

import java.util.List;

/**
 * Created by jmcaldera on 17/08/2017.
 */

public interface CompetitionRepository {

    /**
     * Callback to notify if competions have been loaded or an error ocurred
     */
    interface CompetitionsCallback {

        void onCompetitionsLoaded(List<Competition> competitions);

        void onError(ErrorBundle errorBundle);
    }

    /**
     * Gets a List of {@link Competition}
     * @param callback A {@link CompetitionsCallback} use for notifying users
     */
    void getCompetitions(CompetitionsCallback callback);
}
