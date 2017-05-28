package com.jmcaldera.cleanfootball.data;

import android.support.annotation.NonNull;

import com.jmcaldera.cleanfootball.competitiondetails.model.standings.Standing;
import com.jmcaldera.cleanfootball.competitions.domain.model.Competition;

import java.util.List;

/**
 * Created by jmcaldera on 01/02/2017.
 */

public interface CompetitionsDataSource {

    // Competitions
    interface LoadCompetitionsCallback {

        void onCompetitionsLoaded(List<Competition> competitions);

        void onDataNotAvailable();
    }

    void getCompetitions(@NonNull LoadCompetitionsCallback callback);

    void saveCompetition(@NonNull Competition competition);

    void refreshCompetitions();

    void deleteAllCompetitions();

    // Standings
    interface LoadStandingsCallback {

        void onStandingsLoaded(Standing standing);

        void onDataNotAvailable();
    }

    void getStandings(int competitionId, @NonNull LoadStandingsCallback callback);

    void refreshStandings(int competitionID);
}
