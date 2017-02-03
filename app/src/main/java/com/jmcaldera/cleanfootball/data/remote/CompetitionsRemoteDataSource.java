package com.jmcaldera.cleanfootball.data.remote;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.google.common.collect.Lists;
import com.jmcaldera.cleanfootball.competitions.domain.model.Competition;
import com.jmcaldera.cleanfootball.data.CompetitionsDataSource;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.id;

/**
 * Created by jmcaldera on 01/02/2017.
 */

public class CompetitionsRemoteDataSource implements CompetitionsDataSource {

    private static CompetitionsRemoteDataSource INSTANCE;

    private static final int SERVICE_LATENCY_IN_MILLIS = 2000;

    private static final Map<String, Competition> COMPETITIONS_SERVICE_DATA;

    static {
        COMPETITIONS_SERVICE_DATA = new LinkedHashMap<>(3);
        addCompetition(426, "Premier League 2016/17", "PL");
        addCompetition(430, "1. Bundesliga 2016/17", "BL1");
        addCompetition(436, "Primera Division 2016/17", "PD");
    }

    public static CompetitionsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CompetitionsRemoteDataSource();
        }
        return INSTANCE;
    }

    // Previene instanciacion directa
    private CompetitionsRemoteDataSource () {}

    private static void addCompetition (int id, String caption, String league) {
        Competition competition = new Competition(id, caption, league);
        COMPETITIONS_SERVICE_DATA.put(String.valueOf(competition.getId()), competition);
    }

    /**
     * Note: {@link LoadCompetitionsCallback#onDataNotAvailable()} is never fired. In a real remote data
     * source implementation, this would be fired if the server can't be contacted or the server
     * returns an error.
     */
    @Override
    public void getCompetitions(@NonNull final LoadCompetitionsCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onCompetitionsLoaded(Lists.newArrayList(COMPETITIONS_SERVICE_DATA.values()));
            }
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    @Override
    public void saveCompetition(@NonNull Competition competition) {
        COMPETITIONS_SERVICE_DATA.put(String.valueOf(competition.getId()), competition);
    }

    @Override
    public void refreshCompetitions() {

    }

    // Esta no se usa
    @Override
    public void deleteAllCompetitions() {
        COMPETITIONS_SERVICE_DATA.clear();
    }
}
