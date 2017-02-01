package com.jmcaldera.cleanfootball.competitions.domain.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Objects;


/**
 * Created by jmcaldera on 01/02/2017.
 */

public class Competition {

    @NonNull
    private int mId;

    @Nullable
    private String mCaption;

    @Nullable
    private String mLeague;

    @Nullable
    private String mYear;

    private int mCurrentMatchday;

    private String mLastUpdated;

    public Competition(@NonNull int id, @Nullable String caption, @Nullable String league,
                       @Nullable String year, int currentMatchday, String lastUpdated) {
        this.mId = id;
        this.mCaption = caption;
        this.mLeague = league;
        this.mYear = year;
        this.mCurrentMatchday = currentMatchday;
        this.mLastUpdated = lastUpdated;
    }

    public Competition(@NonNull int id, @Nullable String caption, @Nullable String league) {
        this.mId = id;
        this.mCaption = caption;
        this.mLeague = league;
    }

    @NonNull
    public int getmId() {
        return mId;
    }

    public void setmId(@NonNull int id) {
        this.mId = id;
    }

    @Nullable
    public String getmCaption() {
        return mCaption;
    }

    public void setmCaption(@Nullable String caption) {
        this.mCaption = caption;
    }

    @Nullable
    public String getmLeague() {
        return mLeague;
    }

    public void setmLeague(@Nullable String league) {
        this.mLeague = league;
    }

    @Nullable
    public String getmYear() {
        return mYear;
    }

    public void setmYear(@Nullable String year) {
        this.mYear = year;
    }

    public int getmCurrentMatchday() {
        return mCurrentMatchday;
    }

    public void setmCurrentMatchday(int currentMatchday) {
        this.mCurrentMatchday = currentMatchday;
    }

    public String getmLastUpdated() {
        return mLastUpdated;
    }

    public void setmLastUpdated(String lastUpdated) {
        this.mLastUpdated = lastUpdated;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Competition competition = (Competition) obj;
        return Objects.equal(mId, competition.mId) &&
                Objects.equal(mLeague, competition.mLeague) &&
                Objects.equal(mYear, competition.mYear);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mId, mLeague, mYear);
    }

    @Override
    public String toString() {
        return mCaption;
    }
}
