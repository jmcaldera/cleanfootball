package com.jmcaldera.cleanfootball.competitions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jmcaldera.cleanfootball.R;
import com.jmcaldera.cleanfootball.competitions.domain.model.Competition;
import com.jmcaldera.cleanfootball.util.ScrollChildSwipeRefreshLayout;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jmcaldera on 07/02/17.
 */

public class CompetitionsFragment extends Fragment implements CompetitionsContract.View {

    private CompetitionsContract.Presenter mPresenter;

    private LinearLayout mCompView;

    private View mNoCompView;

    private ImageView mNoCompIcon;

    private TextView mNoCompTitle;

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
        View root = inflater.inflate(R.layout.competitions_frag, container, false);

        // Competitions view
        mCompView = (LinearLayout) root.findViewById(R.id.compLL);
        RecyclerView compList = (RecyclerView) root.findViewById(R.id.comp_list);
        // set adapter

        // No comp view
        mNoCompView = root.findViewById(R.id.no_competitions);
        mNoCompIcon = (ImageView) root.findViewById(R.id.no_comp_icon);
        mNoCompTitle = (TextView) root.findViewById(R.id.no_comp_text);

        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.comp_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        // TODO: chequear si esto es necesario
        swipeRefreshLayout.setScrollUpChild(compList);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadCompetitions(false);
            }
        });

        return root;
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
