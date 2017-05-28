/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jmcaldera.cleanfootball.util;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jmcaldera.cleanfootball.UseCaseHandler;
import com.jmcaldera.cleanfootball.competitiondetails.model.usecase.GetStandings;
import com.jmcaldera.cleanfootball.competitions.domain.usecase.GetCompetitions;
import com.jmcaldera.cleanfootball.data.CompetitionsDataSource;
import com.jmcaldera.cleanfootball.data.CompetitionsRepository;
import com.jmcaldera.cleanfootball.data.local.CompetitionsLocalDataSource;
import com.jmcaldera.cleanfootball.data.remote.CompetitionsRemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Enables injection of mock implementations for
 * {@link CompetitionsDataSource} at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
public class Injection {

    public static CompetitionsRepository provideCompetitionsRepository(@NonNull Context context) {
        checkNotNull(context);
        return CompetitionsRepository.getInstance(CompetitionsRemoteDataSource.getInstance(),
                CompetitionsLocalDataSource.getInstance(context));
    }

    public static GetCompetitions provideGetCompetitions(@NonNull Context context) {
        return new GetCompetitions(provideCompetitionsRepository(context));
    }

    public static GetStandings provideGetStandings(@NonNull Context context) {
        return new GetStandings(provideCompetitionsRepository(context));
    }

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

//    public static GetTask provideGetTask(@NonNull Context context) {
//        return new GetTask(Injection.provideCompetitionsRepository(context));
//    }
//
//    public static SaveTask provideSaveTask(@NonNull Context context) {
//        return new SaveTask(Injection.provideCompetitionsRepository(context));
//    }
//
//    public static CompleteTask provideCompleteTasks(@NonNull Context context) {
//        return new CompleteTask(Injection.provideCompetitionsRepository(context));
//    }
//
//    public static ActivateTask provideActivateTask(@NonNull Context context) {
//        return new ActivateTask(Injection.provideCompetitionsRepository(context));
//    }
//
//    public static ClearCompleteTasks provideClearCompleteTasks(@NonNull Context context) {
//        return new ClearCompleteTasks(Injection.provideCompetitionsRepository(context));
//    }
//
//    public static DeleteTask provideDeleteTask(@NonNull Context context) {
//        return new DeleteTask(Injection.provideCompetitionsRepository(context));
//    }
//
//    public static GetStatistics provideGetStatistics(@NonNull Context context) {
//        return new GetStatistics(Injection.provideCompetitionsRepository(context));
//    }
}
