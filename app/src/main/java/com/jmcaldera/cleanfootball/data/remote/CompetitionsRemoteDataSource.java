package com.jmcaldera.cleanfootball.data.remote;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.common.collect.Lists;
import com.jmcaldera.cleanfootball.competitiondetails.model.standings.Standing;
import com.jmcaldera.cleanfootball.competitions.domain.model.Competition;
import com.jmcaldera.cleanfootball.data.CompetitionsDataSource;
import com.jmcaldera.cleanfootball.data.remote.api.ApiClient;
import com.jmcaldera.cleanfootball.data.remote.api.ApiEndpoints;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.id;

/**
 * Created by jmcaldera on 01/02/2017.
 */

public class CompetitionsRemoteDataSource implements CompetitionsDataSource {

    private static final String TAG = CompetitionsRemoteDataSource.class.getSimpleName();

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
//        Handler handler = new Handler(Looper.getMainLooper());
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                callback.onCompetitionsLoaded(Lists.newArrayList(COMPETITIONS_SERVICE_DATA.values()));
//            }
//        }, SERVICE_LATENCY_IN_MILLIS);

        ApiClient apiClient = new ApiClient();
        ApiEndpoints endpoints = apiClient.establishConnection();

        Call<List<Competition>> competitionsCall = endpoints.getCompetitions();
        competitionsCall.enqueue(new Callback<List<Competition>>() {
            @Override
            public void onResponse(Call<List<Competition>> call, Response<List<Competition>> response) {
                if (!response.isSuccessful()) {
                    // Procesar Error
                    return;
                }

                List<Competition> competitionsResp = response.body();
                if (competitionsResp.size() > 0) {
                    // Enviar lista como respuesta
                    callback.onCompetitionsLoaded(competitionsResp);
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Competition>> call, Throwable t) {
                Log.d(TAG, "Falla en getCompetitions Api: " + t.getMessage());
                callback.onDataNotAvailable();
            }
        });
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

    @Override
    public void getStandings(int competitionId, @NonNull final LoadStandingsCallback callback) {

        ApiClient apiClient = new ApiClient();
        ApiEndpoints endpoints = apiClient.establishConnection();

        Call<Standing> standingCall = endpoints.getStanding(competitionId);

        standingCall.enqueue(new Callback<Standing>() {
            @Override
            public void onResponse(Call<Standing> call, Response<Standing> response) {
                if (!response.isSuccessful()) {
                    // Manejar error
                    return;
                }

                Standing standing = response.body();
                if (standing != null) {
                    callback.onStandingsLoaded(standing);
                }
            }

            @Override
            public void onFailure(Call<Standing> call, Throwable t) {
                Log.d(TAG, "Falla en getStandings Api: " + t.getMessage());
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void refreshStandings(int competitionID) {

    }
}
