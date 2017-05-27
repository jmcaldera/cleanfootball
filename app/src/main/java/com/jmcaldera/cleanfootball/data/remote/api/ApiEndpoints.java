package com.jmcaldera.cleanfootball.data.remote.api;

import com.jmcaldera.cleanfootball.competitions.domain.model.Competition;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jmcaldera on 26/05/17.
 */

public interface ApiEndpoints {

    @GET("competitions")
    Call<List<Competition>> getCompetitions();
}
