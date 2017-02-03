package com.jmcaldera.cleanfootball.competitions.domain.usecase;

import android.support.annotation.NonNull;

import com.jmcaldera.cleanfootball.UseCase;
import com.jmcaldera.cleanfootball.competitions.domain.model.Competition;
import com.jmcaldera.cleanfootball.data.CompetitionsDataSource;
import com.jmcaldera.cleanfootball.data.CompetitionsRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jmcaldera on 02/02/2017.
 * Fetches the list of the competitions
 */

public class GetCompetitions extends UseCase<GetCompetitions.RequestValues, GetCompetitions.ResponseValue> {

    private CompetitionsRepository mCompetitionsRepository;

    public GetCompetitions(@NonNull CompetitionsRepository competitionsRepository) {
        this.mCompetitionsRepository = checkNotNull(competitionsRepository, "competitionsRepository no puede ser null");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        if (requestValues.isForceUpdate()) {
            mCompetitionsRepository.refreshCompetitions();
        }

        mCompetitionsRepository.getCompetitions(new CompetitionsDataSource.LoadCompetitionsCallback() {
            @Override
            public void onCompetitionsLoaded(List<Competition> competitions) {
                ResponseValue responseValue = new ResponseValue(competitions);
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

        public RequestValues(boolean forceUpdate) {
            this.mForceUpdate = forceUpdate;
        }

        public boolean isForceUpdate() {
            return mForceUpdate;
        }

    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final List<Competition> mCompetitions;

        public ResponseValue (@NonNull List<Competition> competitions) {
            mCompetitions = checkNotNull(competitions, "competitions can't be null");
        }

        public List<Competition> getCompetitions() {
            return mCompetitions;
        }
    }
}
