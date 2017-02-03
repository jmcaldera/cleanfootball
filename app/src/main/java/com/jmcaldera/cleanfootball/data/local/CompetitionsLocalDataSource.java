package com.jmcaldera.cleanfootball.data.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jmcaldera.cleanfootball.competitions.domain.model.Competition;
import com.jmcaldera.cleanfootball.data.CompetitionsDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jmcaldera on 02/02/2017.
 * Implementacion de la db en SQLite
 */

public class CompetitionsLocalDataSource implements CompetitionsDataSource {

    private static CompetitionsLocalDataSource INSTANCE;

    private CompetitionsLocalDataSource(@NonNull Context context) {
        checkNotNull(context);
    }

    public static CompetitionsLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CompetitionsLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getCompetitions(@NonNull LoadCompetitionsCallback callback) {

    }

    @Override
    public void saveCompetition(@NonNull Competition competition) {

    }

    @Override
    public void refreshCompetitions() {

    }

    @Override
    public void deleteAllCompetitions() {

    }
}
