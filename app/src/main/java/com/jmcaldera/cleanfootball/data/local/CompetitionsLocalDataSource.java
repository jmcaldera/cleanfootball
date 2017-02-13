package com.jmcaldera.cleanfootball.data.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.common.collect.Lists;
import com.jmcaldera.cleanfootball.competitions.domain.model.Competition;
import com.jmcaldera.cleanfootball.data.CompetitionsDataSource;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jmcaldera on 02/02/2017.
 * Implementacion de la db en SQLite
 */

public class CompetitionsLocalDataSource implements CompetitionsDataSource {

    private static CompetitionsLocalDataSource INSTANCE;

    private static final Map<String, Competition> COMPETITIONS_SERVICE_DATA;

    static {
        COMPETITIONS_SERVICE_DATA = new LinkedHashMap<>(3);
        addCompetition(426, "Premier League 2016/17", "PL");
        addCompetition(430, "1. Bundesliga 2016/17", "BL1");
        addCompetition(436, "Primera Division 2016/17", "PD");
    }

    private static void addCompetition (int id, String caption, String league) {
        Competition competition = new Competition(id, caption, league);
        COMPETITIONS_SERVICE_DATA.put(String.valueOf(competition.getId()), competition);
    }

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
        callback.onCompetitionsLoaded(Lists.newArrayList(COMPETITIONS_SERVICE_DATA.values()));
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
