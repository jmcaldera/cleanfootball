package com.jmcaldera.cleanfootball.competitiondetails;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.jmcaldera.cleanfootball.R;
import com.jmcaldera.cleanfootball.util.ActivityUtils;
import com.jmcaldera.cleanfootball.util.Injection;

public class CompetitionDetailsActivity extends AppCompatActivity {

    private static final String TAG = CompetitionDetailsActivity.class.getSimpleName();

    public static final String EXTRA_COMPETITION_ID = "COMP_ID";

    int competitionId;

//    private CompetitionDetailsPresenter mDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_details);


        // Toolbar
        // TODO: Setear titulo del actionBar al caption de la liga
        setTitle("Liga xxx");
        // Get id
        competitionId = getIntent().getIntExtra(EXTRA_COMPETITION_ID, 0);
        Log.d(TAG, "Competition Id is: " + competitionId);

        //BottomNav
        BottomNavigationView mBottomNav = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });

        StandingsDetailFragment standingsFragment = StandingsDetailFragment.newInstance(competitionId);
        ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(), standingsFragment, R.id.contentFrame);

//        mDetailsPresenter = new CompetitionDetailsPresenter(standingsFragment);
        // TODO: DI
        new CompetitionDetailsPresenter(competitionId,
                standingsFragment,
                Injection.provideGetStandings(getApplicationContext()),
                Injection.provideUseCaseHandler());
        Log.d(TAG, "CompActivity OnCreate");
    }

    private void selectFragment(MenuItem item) {
        Fragment fragment = null;
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        switch (item.getItemId()) {
            case R.id.action_league_table:
                fragment = StandingsDetailFragment.newInstance(competitionId);
                break;
            case R.id.action_fixtures:
                fragment = FixturesDetailFragment.newInstance(competitionId);
                break;
            case R.id.action_teams:

                break;
        }
        if (fragment != null) {
            ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
        }
        new CompetitionDetailsPresenter(competitionId, (CompetitionDetailsContract.View) fragment,
                Injection.provideGetStandings(getApplicationContext()),
                Injection.provideUseCaseHandler());
        Log.d(TAG, "CompAct selectFrag");
    }
}
