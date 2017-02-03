package com.jmcaldera.cleanfootball.competitions;

import android.support.annotation.NonNull;

import com.jmcaldera.cleanfootball.UseCase;
import com.jmcaldera.cleanfootball.UseCaseHandler;
import com.jmcaldera.cleanfootball.competitions.domain.model.Competition;
import com.jmcaldera.cleanfootball.competitions.domain.usecase.GetCompetitions;

import java.util.List;

import static android.R.attr.process;
import static android.R.string.no;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jmcaldera on 02/02/2017.
 */

public class CompetitionsPresenter implements CompetitionsContract.Presenter {

    private final CompetitionsContract.View mCompetitionsView;
    private final GetCompetitions mGetCompetitions;

    private boolean mFirstLoad = true;

    private final UseCaseHandler mUseCaseHandler;

    public CompetitionsPresenter(@NonNull CompetitionsContract.View competitionsView,
                                 @NonNull GetCompetitions getCompetitions,
                                 @NonNull UseCaseHandler useCaseHandler) {
        this.mCompetitionsView = checkNotNull(competitionsView, "competitionsView no puede ser null");
        this.mGetCompetitions = checkNotNull(getCompetitions, "getCompetitions no puede ser null");
        this.mUseCaseHandler = checkNotNull(useCaseHandler, "useCaseHandler no puede ser null");

        mCompetitionsView.setPresenter(this);
    }

    @Override
    public void start() {
        loadCompetitions(false);
    }

    @Override
    public void loadCompetitions(boolean forceUpdate) {
        loadCompetitions(forceUpdate || mFirstLoad, true);
    }

    private void loadCompetitions(boolean forceUpdate, final boolean showLoading) {
        if (showLoading) {
            mCompetitionsView.setLoadingIndicator(true);
        }

        GetCompetitions.RequestValues requestValues = new GetCompetitions.RequestValues(forceUpdate);

        mUseCaseHandler.execute(mGetCompetitions, requestValues,
                new UseCase.UseCaseCallback<GetCompetitions.ResponseValue>() {
            @Override
            public void onSuccess(GetCompetitions.ResponseValue response) {
                List<Competition> competitions = response.getCompetitions();

                if (!mCompetitionsView.isActive()) {
                    return;
                }
                if (showLoading) {
                    mCompetitionsView.setLoadingIndicator(false);
                }
                processCompetitions(competitions);
            }

            @Override
            public void onError() {
                if (!mCompetitionsView.isActive()) {
                    return;
                }
                mCompetitionsView.showLoadingCompetitionsError();
            }
        });
    }

    private void processCompetitions(List<Competition> competitions) {
        mCompetitionsView.showCompetitions(competitions);
    }

    @Override
    public void openCompetition(@NonNull Competition competition) {
        checkNotNull(competition, "competicion no puede ser null");

    }
}
