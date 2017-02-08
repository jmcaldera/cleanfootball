package com.jmcaldera.cleanfootball.competitions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jmcaldera.cleanfootball.competitions.domain.model.Competition;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jmcaldera on 07/02/17.
 */

public class CompetitionsFragment extends Fragment implements CompetitionsContract.View {

    private CompetitionsContract.Presenter mPresenter;


    public CompetitionsFragment () {}

    public static CompetitionsFragment newInstance() {
        return new CompetitionsFragment();
    }

    @Override
    public void setPresenter(CompetitionsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    CompetitionItemListener mItemListener = new CompetitionItemListener() {
        @Override
        public void onCompetitionClick(Competition competition) {
            mPresenter.openCompetition(competition);
        }
    };


    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showCompetitions(List<Competition> competitions) {

    }

    @Override
    public void showCompetitionsDetails(int id) {

    }

    @Override
    public void showLoadingCompetitionsError() {

    }

    @Override
    public void showNoCompetitions() {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    public interface CompetitionItemListener {

        void onCompetitionClick(Competition competition);

    }
}
