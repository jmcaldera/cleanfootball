package com.jmcaldera.cleanfootball.competitiondetails.model.usecase;

import android.support.annotation.NonNull;

import com.jmcaldera.cleanfootball.UseCase;
import com.jmcaldera.cleanfootball.competitiondetails.model.standings.Standing;
import com.jmcaldera.cleanfootball.data.CompetitionsDataSource;
import com.jmcaldera.cleanfootball.data.CompetitionsRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jmcaldera on 26/05/17.
 */

public class GetStandings extends UseCase<GetStandings.RequestValues, GetStandings.ResponseValue> {

    private CompetitionsRepository mCompetitionsRepository;

    public GetStandings(@NonNull CompetitionsRepository competitionsRepository) {
        this.mCompetitionsRepository = checkNotNull(competitionsRepository, "repository no puede ser null");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        // TODO: execute
        if (requestValues.isForceUpdate()) {
            mCompetitionsRepository.refreshStandings(requestValues.getCompetitionId());
        }

        mCompetitionsRepository.getStandings(requestValues.getCompetitionId(), new CompetitionsDataSource.LoadStandingsCallback() {
            @Override
            public void onStandingsLoaded(Standing standing) {
                ResponseValue responseValue = new ResponseValue(standing);
                getUseCaseCallback().onSuccess(responseValue);
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final boolean mForceUpdate;
        private final int competitionId;

        public RequestValues(boolean forceUpdate, int id) {
            this.mForceUpdate = forceUpdate;
            this.competitionId = id;
        }

        public boolean isForceUpdate() {
            return mForceUpdate;
        }

        public int getCompetitionId() {
            return competitionId;
        }

    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final Standing standing;

        public ResponseValue(@NonNull Standing standing) {
            this.standing = checkNotNull(standing, "standings no puede ser null");
        }

        public Standing getStanding() {
            return standing;
        }
    }
}
