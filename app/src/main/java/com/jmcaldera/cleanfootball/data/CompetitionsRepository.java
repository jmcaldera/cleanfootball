package com.jmcaldera.cleanfootball.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.jmcaldera.cleanfootball.competitiondetails.model.standings.Standing;
import com.jmcaldera.cleanfootball.competitions.domain.model.Competition;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jmcaldera on 02/02/2017.
 */

public class CompetitionsRepository implements CompetitionsDataSource {

    private static final String TAG = CompetitionsRepository.class.getSimpleName();

    private static CompetitionsRepository INSTANCE = null;

    private final CompetitionsDataSource mCompetitionsRemoteDataSource;

    private final CompetitionsDataSource mCompetitionsLocalDataSource;


    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<String, Competition> mCachedCompetitions;
    Map<String, Standing> mCachedStandings;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;
    boolean mStandingsCacheIsDirty = false;

    // Evita instanciamiento directo
    private CompetitionsRepository(@NonNull CompetitionsDataSource competitionsRemoteDataSource,
                                  @NonNull CompetitionsDataSource competitionsLocalDataSource) {
        this.mCompetitionsRemoteDataSource = checkNotNull(competitionsRemoteDataSource);
        this.mCompetitionsLocalDataSource = checkNotNull(competitionsLocalDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param competitionsRemoteDataSource the backend data source
     * @param competitionsLocalDataSource  the device storage data source
     * @return the {@link CompetitionsRepository} instance
     */
    public static CompetitionsRepository getInstance(CompetitionsDataSource competitionsRemoteDataSource,
                                                     CompetitionsDataSource competitionsLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new CompetitionsRepository(competitionsRemoteDataSource, competitionsLocalDataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(CompetitionsDataSource, CompetitionsDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }


    @Override
    public void getCompetitions(@NonNull final LoadCompetitionsCallback callback) {
        checkNotNull(callback);

        if (mCachedCompetitions != null && !mCacheIsDirty) {
            callback.onCompetitionsLoaded(new ArrayList<>(mCachedCompetitions.values()));
            Log.d(TAG, "Competitions loaded from Cache");
            return;
        }

        if (mCacheIsDirty) {
            // Fetch new data from remote
            getCompetitionsFromRemoteDataSource(callback);
        } else {
            // Query the local storage if available. If not, query the network.
            mCompetitionsLocalDataSource.getCompetitions(new LoadCompetitionsCallback() {
                @Override
                public void onCompetitionsLoaded(List<Competition> competitions) {
                    refreshCache(competitions);
                    callback.onCompetitionsLoaded(new ArrayList<>(mCachedCompetitions.values()));
                    Log.d(TAG, "Competitions loaded form LocalDataSource");
                }

                @Override
                public void onDataNotAvailable() {
                    getCompetitionsFromRemoteDataSource(callback);
                }
            });
        }
    }

    @Override
    public void saveCompetition(@NonNull Competition competition) {
        checkNotNull(competition);
        mCompetitionsLocalDataSource.saveCompetition(competition);

        if (mCachedCompetitions == null) {
            mCachedCompetitions = new LinkedHashMap<>();
        }
        mCachedCompetitions.put(String.valueOf(competition.getId()), competition);
    }

    @Override
    public void refreshCompetitions() {
        mCacheIsDirty = true;
    }

    @Override
    public void deleteAllCompetitions() {
        mCompetitionsLocalDataSource.deleteAllCompetitions();

        if (mCachedCompetitions == null) {
            mCachedCompetitions = new LinkedHashMap<>();
        }
        mCachedCompetitions.clear();
    }

    private void getCompetitionsFromRemoteDataSource(@NonNull final LoadCompetitionsCallback callback) {
        mCompetitionsRemoteDataSource.getCompetitions(new LoadCompetitionsCallback() {
            @Override
            public void onCompetitionsLoaded(List<Competition> competitions) {
                refreshCache(competitions);
                refreshLocalDataSource(competitions);
                callback.onCompetitionsLoaded(new ArrayList<>(mCachedCompetitions.values()));
                Log.d(TAG, "Competitions loaded from RemoteDataSource");
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshCache(List<Competition> competitions) {
        if (mCachedCompetitions == null) {
            mCachedCompetitions = new LinkedHashMap<>();
        }
        mCachedCompetitions.clear();
        for (Competition competition : competitions) {
            mCachedCompetitions.put(String.valueOf(competition.getId()), competition);
        }
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(List<Competition> competitions) {
        mCompetitionsLocalDataSource.deleteAllCompetitions();
        for (Competition competition : competitions) {
            mCompetitionsLocalDataSource.saveCompetition(competition);
        }
    }

    @Override
    public void getStandings(final int competitionId, @NonNull final LoadStandingsCallback callback) {
        checkNotNull(callback, "callback no puede ser null");

        Log.d(TAG, "cached: " + mCachedStandings != null?"true":"false");
        Log.d(TAG, "isDirty: " + mStandingsCacheIsDirty);

        if (mCachedStandings != null && !mStandingsCacheIsDirty) {
            if (mCachedStandings.containsKey(String.valueOf(competitionId))) {
                Standing cacheStanding = mCachedStandings.get(String.valueOf(competitionId));
                Log.d(TAG, "TRUE, " + cacheStanding.toString());
//            if (cacheStanding != null) {
                // Busca la tabla para la competicion dada, si esta en cache, la regresa
                callback.onStandingsLoaded(cacheStanding);
                Log.d(TAG, "Standing loaded from Cache");
            }
                return;
//            } else {
//                // si no esta en cache, entonces debe buscar en remote
//                Log.d(TAG, "myCacheIsDirty");
//                mStandingsCacheIsDirty = true;
//            }
        }
        if (mStandingsCacheIsDirty) {
            //Fetch from remote
            Log.d(TAG, "Fetch remote");
            getStandingsFromRemoteDataSource(competitionId, callback);
        } else {
            mCompetitionsLocalDataSource.getStandings(competitionId, new LoadStandingsCallback() {
                @Override
                public void onStandingsLoaded(Standing standing) {
                    refreshStandingsCache(competitionId, standing);
                    callback.onStandingsLoaded(standing);
                    Log.d(TAG, "Standing loaded from LocalDataSource");
                }

                @Override
                public void onDataNotAvailable() {
                    callback.onDataNotAvailable();
                }
            });
        }
    }

    private void getStandingsFromRemoteDataSource(final int competitionId,
                                                  @NonNull final LoadStandingsCallback callback) {

        mCompetitionsRemoteDataSource.getStandings(competitionId, new LoadStandingsCallback() {
            @Override
            public void onStandingsLoaded(Standing standing) {
                refreshStandingsCache(competitionId, standing);
                refreshStandingsLocalDataSource(competitionId, standing);
                callback.onStandingsLoaded(standing);
                Log.d(TAG, "Standings loaded from RemoteDataSource");
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshStandingsCache(int id, Standing standing) {
        if (mCachedStandings == null) {
            mCachedStandings = new LinkedHashMap<>();
        }
//        mCachedStandings.clear();
        mCachedStandings.put(String.valueOf(id), standing);
        mStandingsCacheIsDirty = false;
    }

    private void refreshStandingsLocalDataSource(int id, Standing standing) {
        // Borrar el standing dado por id
        //Guarda el nuevo standing
    }

    @Override
    public void refreshStandings(int competitionID) {
        if (mCachedStandings == null || mCachedStandings.isEmpty()
                || !mCachedStandings.containsKey(String.valueOf(competitionID))) {
            mStandingsCacheIsDirty = true;
        }
    }
}
