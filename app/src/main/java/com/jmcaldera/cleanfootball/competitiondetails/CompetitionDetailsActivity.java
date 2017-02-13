package com.jmcaldera.cleanfootball.competitiondetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jmcaldera.cleanfootball.R;

public class CompetitionDetailsActivity extends AppCompatActivity {

    private static final String TAG = CompetitionDetailsActivity.class.getSimpleName();

    public static final String EXTRA_COMPETITION_ID = "COMP_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_details);


        // Toolbar

        // Get id
        int competitionId = getIntent().getIntExtra(EXTRA_COMPETITION_ID, 0);
        Log.d(TAG, "Competition Id is: " + competitionId);
    }
}
