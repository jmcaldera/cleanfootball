package com.jmcaldera.cleanfootball.competitiondetails;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jmcaldera on 16/02/17.
 */

public class CompetitionDetailsPresenter implements CompetitionDetailsContract.Presenter {

    private CompetitionDetailsContract.View mStandingsView;
    private CompetitionDetailsContract.View mFixturesView;

    public CompetitionDetailsPresenter() {
    }

    public CompetitionDetailsPresenter(CompetitionDetailsContract.View standingsView) {
        this.mStandingsView = checkNotNull(standingsView, "standingsView no puede ser null");
//        mStandingsView.setPresenter(this);
    }

    public CompetitionDetailsPresenter(CompetitionDetailsContract.View standingsView,
                                       CompetitionDetailsContract.View fixturesView) {
        this.mStandingsView = checkNotNull(standingsView, "standingsView no puede ser null");
        this.mFixturesView = checkNotNull(fixturesView, "fixturesView no puede ser null");
    }

    @Override
    public void bind(CompetitionDetailsContract.View view) {
        this.mStandingsView = view;
        this.mStandingsView.setPresenter(this);
    }

    @Override
    public void unbind() {
        this.mStandingsView = null;
    }

    @Override
    public void loadStandings(boolean forceUpdate) {
        mStandingsView.showStandings();
    }

    @Override
    public void loadFixtures(boolean forceUpdate) {

    }

    @Override
    public void loadTeams(boolean forceUpdate) {

    }

    @Override
    public void openTeam() {
        mFixturesView.showFixtures();
    }

    @Override
    public void start() {

    }
}
