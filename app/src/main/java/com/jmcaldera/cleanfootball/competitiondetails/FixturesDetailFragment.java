package com.jmcaldera.cleanfootball.competitiondetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jmcaldera.cleanfootball.R;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jmcaldera on 16/02/17.
 */

public class FixturesDetailFragment extends Fragment implements CompetitionDetailsContract.View {

    @NonNull
    private static final String ARGUMENT_COMPETITION_ID = "COMP_ID";

    private CompetitionDetailsContract.Presenter mPresenter;

    public static FixturesDetailFragment newInstance(int id) {
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_COMPETITION_ID, id);
        FixturesDetailFragment fragment = new FixturesDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(CompetitionDetailsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.standings_frag, container, false);
        TextView textView = (TextView) root.findViewById(R.id.stand_text);
        textView.setText("Fixtures");

        Button button = (Button) root.findViewById(R.id.stand_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.openTeam();
            }
        });
        return root;
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showStandings() {

    }

    @Override
    public void showFixtures() {
        if (getView() == null) {
            return;
        }
        Snackbar.make(getView(), "click en fixtures", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean isActive() {
        return false;
    }
}