package com.jmcaldera.cleanfootball.competitions.domain.model;

import com.google.gson.annotations.SerializedName;

public class Links{

	@SerializedName("teams")
	private Teams teams;

	@SerializedName("leagueTable")
	private LeagueTable leagueTable;

	@SerializedName("self")
	private Self self;

	@SerializedName("fixtures")
	private Fixtures fixtures;

	public void setTeams(Teams teams){
		this.teams = teams;
	}

	public Teams getTeams(){
		return teams;
	}

	public void setLeagueTable(LeagueTable leagueTable){
		this.leagueTable = leagueTable;
	}

	public LeagueTable getLeagueTable(){
		return leagueTable;
	}

	public void setSelf(Self self){
		this.self = self;
	}

	public Self getSelf(){
		return self;
	}

	public void setFixtures(Fixtures fixtures){
		this.fixtures = fixtures;
	}

	public Fixtures getFixtures(){
		return fixtures;
	}

	@Override
 	public String toString(){
		return 
			"Links{" + 
			"teams = '" + teams + '\'' + 
			",leagueTable = '" + leagueTable + '\'' + 
			",self = '" + self + '\'' + 
			",fixtures = '" + fixtures + '\'' + 
			"}";
		}
}