package com.jmcaldera.cleanfootball.competitiondetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jmcaldera on 12/02/17.
 */

public class CompetitionDetailFragment extends Fragment implements CompetitionDetailsContract.View{

    @NonNull
    private static final String ARGUMENT_COMPETITION_ID = "COMP_ID";

    private CompetitionDetailsContract.Presenter mPresenter;

    public static CompetitionDetailFragment newInstance(int id) {
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_COMPETITION_ID, id);
        CompetitionDetailFragment fragment = new CompetitionDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void setPresenter(@NonNull CompetitionDetailsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showStandings() {

    }

    @Override
    public void showFixtures() {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
