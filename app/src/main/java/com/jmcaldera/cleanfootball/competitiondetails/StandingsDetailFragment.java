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
 * Created by jmcaldera on 12/02/17.
 */

public class StandingsDetailFragment extends Fragment implements CompetitionDetailsContract.View{

    @NonNull
    private static final String ARGUMENT_COMPETITION_ID = "COMP_ID";

    private CompetitionDetailsContract.Presenter mPresenter;

    public StandingsDetailFragment() {}

    public static StandingsDetailFragment newInstance(int id) {
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_COMPETITION_ID, id);
        StandingsDetailFragment fragment = new StandingsDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull CompetitionDetailsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.standings_frag, container, false);

        TextView textView = (TextView) root.findViewById(R.id.standings_text);
        textView.setText("Standings");

        Button button = (Button) root.findViewById(R.id.standings_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.loadStandings(true);
            }
        });

        return root;
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showStandings() {
        if (getView() == null) {
            return;
        }
        Snackbar.make(getView(), "click en Standings", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showFixtures() {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
