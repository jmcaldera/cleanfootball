package com.jmcaldera.cleanfootball.competitiondetails;

import android.support.annotation.NonNull;

import com.jmcaldera.cleanfootball.UseCase;
import com.jmcaldera.cleanfootball.UseCaseHandler;
import com.jmcaldera.cleanfootball.competitiondetails.model.standings.Standing;
import com.jmcaldera.cleanfootball.competitiondetails.model.standings.StandingItem;
import com.jmcaldera.cleanfootball.competitiondetails.model.usecase.GetStandings;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jmcaldera on 16/02/17.
 */

public class CompetitionDetailsPresenter implements CompetitionDetailsContract.Presenter {

    private CompetitionDetailsContract.View mView;
    private GetStandings mGetStandings;

    private boolean mFirstLoad = true;
    int competitionId;

    private UseCaseHandler mUseCaseHandler;

    public CompetitionDetailsPresenter(int competitionId,
                                       @NonNull CompetitionDetailsContract.View view,
                                       @NonNull GetStandings getStandings,
                                       @NonNull UseCaseHandler useCaseHandler) {
        this.mView = checkNotNull(view, "view no puede ser null");
        this.mGetStandings = checkNotNull(getStandings, "getStandings no puede ser null");
        this.mUseCaseHandler = checkNotNull(useCaseHandler, "useCaseHandler no puede ser null");
        this.competitionId = competitionId;
        mView.setPresenter(this);
    }

    @Override
    public void stop() {
        this.mView = null;
    }

    @Override
    public void loadStandings(boolean forceUpdate) {
        loadStandings(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private void loadStandings(boolean forceUpdate, final boolean showLoading) {
        if(showLoading) {
            mView.setLoadingIndicator(true);
        }

        GetStandings.RequestValues requestValues =
                new GetStandings.RequestValues(forceUpdate, competitionId);

        mUseCaseHandler.execute(mGetStandings,
                requestValues,
                new UseCase.UseCaseCallback<GetStandings.ResponseValue>() {
            @Override
            public void onSuccess(GetStandings.ResponseValue response) {
                Standing standing = response.getStanding();

                if (!mView.isActive()) {
                    return;
                }

                if (showLoading) {
                    mView.setLoadingIndicator(false);
                }

                processStandings(standing);
            }

            @Override
            public void onError() {
                if (!mView.isActive()) {
                    return;
                }
                mView.showLoadingStandingsError();
            }
        });
    }

    private void processStandings(Standing standing) {
        List<StandingItem> standingItems = standing.getStanding();
        // Mapper para mostrar la data interesante. Luego enviar a la vista
        mView.showStandings(standingItems);
    }

    @Override
    public void loadFixtures(boolean forceUpdate) {

    }

    @Override
    public void loadTeams(boolean forceUpdate) {

    }

    @Override
    public void openTeam() {
        mView.showFixtures();
    }

    @Override
    public void start() {
        loadStandings(false);
    }
}
