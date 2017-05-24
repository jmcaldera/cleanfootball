package com.jmcaldera.cleanfootball.competitiondetails;

import com.jmcaldera.cleanfootball.BasePresenter;
import com.jmcaldera.cleanfootball.BaseView;

/**
 * Created by jmcaldera on 12/02/17.
 */

public interface CompetitionDetailsContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        // TODO: crear standings model y fixtures model
        void showStandings();

        void showFixtures();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void bind(View view);

        void unbind();

        void loadStandings(boolean forceUpdate);

        void loadFixtures(boolean forceUpdate);

        void loadTeams(boolean forceUpdate);

        // TODO: crear team model
        void openTeam();
    }
}
