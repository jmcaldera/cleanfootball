package com.jmcaldera.cleanfootball.data;

import android.support.annotation.NonNull;

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

    private static CompetitionsRepository INSTANCE = null;

    private final CompetitionsDataSource mCompetitionsRemoteDataSource;

    private final CompetitionsDataSource mCompetitionsLocalDataSource;


    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<String, Competition> mCachedCompetitions;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

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
}
