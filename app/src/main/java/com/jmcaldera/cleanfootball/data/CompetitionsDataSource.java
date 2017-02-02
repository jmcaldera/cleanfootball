package com.jmcaldera.cleanfootball.data;

import android.support.annotation.NonNull;

import com.jmcaldera.cleanfootball.competitions.domain.model.Competition;

import java.util.List;

/**
 * Created by jmcaldera on 01/02/2017.
 */

public interface CompetitionsDataSource {

    interface LoadCompetitionsCallback {

        void onCompetitionsLoaded(List<Competition> competitions);

        void onDataNotAvailable();
    }

    void getCompetitions(@NonNull LoadCompetitionsCallback callback);
}
