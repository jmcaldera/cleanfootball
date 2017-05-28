package com.jmcaldera.cleanfootball.competitiondetails.model.standings;

import com.google.gson.annotations.SerializedName;

public class Links{

	@SerializedName("self")
	private Self self;

	@SerializedName("competition")
	private Competition competition;

	public void setSelf(Self self){
		this.self = self;
	}

	public Self getSelf(){
		return self;
	}

	public void setCompetition(Competition competition){
		this.competition = competition;
	}

	public Competition getCompetition(){
		return competition;
	}

	@Override
 	public String toString(){
		return 
			"Links{" + 
			"self = '" + self + '\'' + 
			",competition = '" + competition + '\'' + 
			"}";
		}
}