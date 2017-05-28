package com.jmcaldera.cleanfootball.competitiondetails.model.standings;

import com.google.gson.annotations.SerializedName;

public class Away{

	@SerializedName("wins")
	private int wins;

	@SerializedName("draws")
	private int draws;

	@SerializedName("goalsAgainst")
	private int goalsAgainst;

	@SerializedName("losses")
	private int losses;

	@SerializedName("goals")
	private int goals;

	public void setWins(int wins){
		this.wins = wins;
	}

	public int getWins(){
		return wins;
	}

	public void setDraws(int draws){
		this.draws = draws;
	}

	public int getDraws(){
		return draws;
	}

	public void setGoalsAgainst(int goalsAgainst){
		this.goalsAgainst = goalsAgainst;
	}

	public int getGoalsAgainst(){
		return goalsAgainst;
	}

	public void setLosses(int losses){
		this.losses = losses;
	}

	public int getLosses(){
		return losses;
	}

	public void setGoals(int goals){
		this.goals = goals;
	}

	public int getGoals(){
		return goals;
	}

	@Override
 	public String toString(){
		return 
			"Away{" + 
			"wins = '" + wins + '\'' + 
			",draws = '" + draws + '\'' + 
			",goalsAgainst = '" + goalsAgainst + '\'' + 
			",losses = '" + losses + '\'' + 
			",goals = '" + goals + '\'' + 
			"}";
		}
}