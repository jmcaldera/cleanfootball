package com.jmcaldera.cleanfootball.data.remote;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.google.common.collect.Lists;
import com.jmcaldera.cleanfootball.competitions.domain.model.Competition;
import com.jmcaldera.cleanfootball.data.CompetitionsDataSource;

import java.util.List;

/**
 * Created by jmcaldera on 01/02/2017.
 */

public class CompetitionsRemoteDataSource implements CompetitionsDataSource {

    @Override
    public void getCompetitions(@NonNull final LoadCompetitionsCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 1000);
    }
}
