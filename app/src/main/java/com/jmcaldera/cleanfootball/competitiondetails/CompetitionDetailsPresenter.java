package com.jmcaldera.cleanfootball.competitiondetails;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jmcaldera on 16/02/17.
 */

public class CompetitionDetailsPresenter implements CompetitionDetailsContract.Presenter {

    private CompetitionDetailsContract.View mView;


    public CompetitionDetailsPresenter(CompetitionDetailsContract.View view) {
        this.mView = checkNotNull(view, "view no puede ser null");
        mView.setPresenter(this);
    }

    @Override
    public void stop() {
        this.mView = null;
    }

    @Override
    public void loadStandings(boolean forceUpdate) {
        mView.showStandings();
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

    }
}
