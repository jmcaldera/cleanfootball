package com.jmcaldera.cleanfootball.competitions.domain.usecase;

import android.support.annotation.NonNull;

import com.jmcaldera.cleanfootball.UseCase;
import com.jmcaldera.cleanfootball.competitions.domain.model.Competition;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jmcaldera on 02/02/2017.
 * Fetches the list of the competitions
 */

public class GetCompetitions extends UseCase<GetCompetitions.RequestValues, GetCompetitions.ResponseValue> {


    @Override
    protected void executeUseCase(RequestValues requestValues) {


    }

    public static final class RequestValues implements UseCase.RequestValues {

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
