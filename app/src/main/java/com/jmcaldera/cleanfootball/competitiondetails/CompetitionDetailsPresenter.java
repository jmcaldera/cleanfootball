package com.jmcaldera.cleanfootball.competitiondetails;

/**
 * Created by jmcaldera on 16/02/17.
 */

public class CompetitionDetailsPresenter implements CompetitionDetailsContract.Presenter {

    private CompetitionDetailsContract.View mStandingsView;
    private CompetitionDetailsContract.View mFixturesView;

    public CompetitionDetailsPresenter(CompetitionDetailsContract.View mStandingsView) {
        this.mStandingsView = mStandingsView;
    }

    public CompetitionDetailsPresenter(CompetitionDetailsContract.View mStandingsView,
                                       CompetitionDetailsContract.View mFixturesView) {
        this.mStandingsView = mStandingsView;
        this.mFixturesView = mFixturesView;
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
