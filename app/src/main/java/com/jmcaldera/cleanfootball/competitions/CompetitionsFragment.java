package com.jmcaldera.cleanfootball.competitions;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jmcaldera.cleanfootball.R;
import com.jmcaldera.cleanfootball.competitiondetails.CompetitionDetailsActivity;
import com.jmcaldera.cleanfootball.competitions.domain.model.Competition;
import com.jmcaldera.cleanfootball.util.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jmcaldera on 07/02/17.
 */

public class CompetitionsFragment extends Fragment implements CompetitionsContract.View {

    private static final String TAG = CompetitionsFragment.class.getSimpleName();

    private CompetitionsContract.Presenter mPresenter;

    private LinearLayout mCompView;

    private View mNoCompView;

    private ImageView mNoCompIcon;

    private TextView mNoCompTitle;

    private CompetitionsAdapter mCompetitionsAdapter;

    public CompetitionsFragment () {}

    public static CompetitionsFragment newInstance() {
        return new CompetitionsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCompetitionsAdapter = new CompetitionsAdapter(new ArrayList<Competition>(0), mItemListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(CompetitionsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.competitions_frag, container, false);

        // Competitions view
        mCompView = (LinearLayout) root.findViewById(R.id.compLL);
        RecyclerView competitionsList = (RecyclerView) root.findViewById(R.id.comp_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        competitionsList.setLayoutManager(layoutManager);   // LayoutManager Vertical
        competitionsList.setAdapter(mCompetitionsAdapter);  // set RV Adapter

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
        swipeRefreshLayout.setScrollUpChild(competitionsList);

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
    public void setLoadingIndicator(final boolean active) {

        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout refreshLayout =
                (SwipeRefreshLayout) getView().findViewById(R.id.comp_refresh_layout);

        // TODO: runnable?
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(active);
            }
        });

    }

    @Override
    public void showCompetitions(List<Competition> competitions) {
        mCompetitionsAdapter.replaceData(competitions);

        mCompView.setVisibility(View.VISIBLE);
        mNoCompView.setVisibility(View.GONE);
    }

    @Override
    public void showCompetitionsDetails(int id) {
        // Start activity con intent.putExtra(id)
        // TODO: startActivity
        Log.d(TAG , "Click en competition: " + id);
        Intent intent = new Intent(getContext(), CompetitionDetailsActivity.class);
        intent.putExtra(CompetitionDetailsActivity.EXTRA_COMPETITION_ID, id);
        startActivity(intent);
    }

    @Override
    public void showLoadingCompetitionsError() {
        showMessage("Error al cargar competiciones");
    }

    private void showMessage(String msg) {
        if (getView() == null) {
            return;
        }
        Snackbar.make(getView(), msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showNoCompetitions() {
        mCompView.setVisibility(View.GONE);
        mNoCompView.setVisibility(View.VISIBLE);

        mNoCompTitle.setText("No hay competiciones");
        //TODO: setear drawable al icon
//        mNoCompIcon.setImageDrawable(getResources().getDrawable());

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    public class CompetitionsAdapter extends RecyclerView.Adapter<CompetitionsAdapter.CompetitionViewHolder> {
        private List<Competition> mCompetitions;

        private CompetitionItemListener mItemListener;

        public CompetitionsAdapter(List<Competition> competitions, CompetitionItemListener itemListener) {
            setList(competitions);
            this.mItemListener = itemListener;
        }

        public void replaceData(List<Competition> competitions) {
            setList(competitions);
            notifyDataSetChanged();
        }

        private void setList(List<Competition> competitions) {
            mCompetitions = checkNotNull(competitions);
        }

        @Override
        public CompetitionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.competition_item, parent, false);

            return new CompetitionViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CompetitionViewHolder holder, int position) {

            final Competition competition = mCompetitions.get(position);
            holder.captionText.setText(competition.getCaption());

            holder.captionText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemListener.onCompetitionClick(competition);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mCompetitions.size();
        }

        public class CompetitionViewHolder extends RecyclerView.ViewHolder {
            // TODO: imageview, textview del layout para competition
            TextView captionText;

            public CompetitionViewHolder(View itemView) {
                super(itemView);
                // TODO: findview del layout
                captionText = (TextView) itemView.findViewById(R.id.caption);
            }
        }
    }

    public interface CompetitionItemListener {

        void onCompetitionClick(Competition competition);

    }
}
