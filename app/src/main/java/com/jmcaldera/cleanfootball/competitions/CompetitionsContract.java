package com.jmcaldera.cleanfootball.competitions;

import android.support.annotation.NonNull;

import com.jmcaldera.cleanfootball.BasePresenter;
import com.jmcaldera.cleanfootball.BaseView;
import com.jmcaldera.cleanfootball.competitions.domain.model.Competition;

import java.util.List;

/**
 * Created by jmcaldera on 02/02/2017.
 */

public interface CompetitionsContract {

    interface  View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showCompetitions(List<Competition> competitions);

        void showCompetitionsDetails(int id);

        void showLoadingCompetitionsError();

        void showNoCompetitions();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void loadCompetitions(boolean forceUpdate);

        void openCompetition(@NonNull Competition competition);

    }
}
