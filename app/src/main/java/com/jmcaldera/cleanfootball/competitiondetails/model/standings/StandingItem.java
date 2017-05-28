package com.jmcaldera.cleanfootball.competitiondetails.model.standings;

import com.google.gson.annotations.SerializedName;

public class StandingItem{

	@SerializedName("teamName")
	private String teamName;

	@SerializedName("wins")
	private int wins;

	@SerializedName("away")
	private Away away;

	@SerializedName("_links")
	private Links links;

	@SerializedName("crestURI")
	private String crestURI;

	@SerializedName("losses")
	private int losses;

	@SerializedName("points")
	private int points;

	@SerializedName("home")
	private Home home;

	@SerializedName("playedGames")
	private int playedGames;

	@SerializedName("draws")
	private int draws;

	@SerializedName("position")
	private int position;

	@SerializedName("goalsAgainst")
	private int goalsAgainst;

	@SerializedName("goalDifference")
	private int goalDifference;

	@SerializedName("goals")
	private int goals;

	public void setTeamName(String teamName){
		this.teamName = teamName;
	}

	public String getTeamName(){
		return teamName;
	}

	public void setWins(int wins){
		this.wins = wins;
	}

	public int getWins(){
		return wins;
	}

	public void setAway(Away away){
		this.away = away;
	}

	public Away getAway(){
		return away;
	}

	public void setLinks(Links links){
		this.links = links;
	}

	public Links getLinks(){
		return links;
	}

	public void setCrestURI(String crestURI){
		this.crestURI = crestURI;
	}

	public String getCrestURI(){
		return crestURI;
	}

	public void setLosses(int losses){
		this.losses = losses;
	}

	public int getLosses(){
		return losses;
	}

	public void setPoints(int points){
		this.points = points;
	}

	public int getPoints(){
		return points;
	}

	public void setHome(Home home){
		this.home = home;
	}

	public Home getHome(){
		return home;
	}

	public void setPlayedGames(int playedGames){
		this.playedGames = playedGames;
	}

	public int getPlayedGames(){
		return playedGames;
	}

	public void setDraws(int draws){
		this.draws = draws;
	}

	public int getDraws(){
		return draws;
	}

	public void setPosition(int position){
		this.position = position;
	}

	public int getPosition(){
		return position;
	}

	public void setGoalsAgainst(int goalsAgainst){
		this.goalsAgainst = goalsAgainst;
	}

	public int getGoalsAgainst(){
		return goalsAgainst;
	}

	public void setGoalDifference(int goalDifference){
		this.goalDifference = goalDifference;
	}

	public int getGoalDifference(){
		return goalDifference;
	}

	public void setGoals(int goals){
		this.goals = goals;
	}

	public int getGoals(){
		return goals;
	}

	public StandingItem(String teamName, int position, int points,int wins, int losses, int draws) {
		this.teamName = teamName;
		this.position = position;
		this.points = points;
		this.wins = wins;
		this.losses = losses;
		this.draws = draws;
	}

	@Override
 	public String toString(){
		return 
			"StandingItem{" + 
			"teamName = '" + teamName + '\'' + 
			",wins = '" + wins + '\'' + 
			",away = '" + away + '\'' + 
			",_links = '" + links + '\'' + 
			",crestURI = '" + crestURI + '\'' + 
			",losses = '" + losses + '\'' + 
			",points = '" + points + '\'' + 
			",home = '" + home + '\'' + 
			",playedGames = '" + playedGames + '\'' + 
			",draws = '" + draws + '\'' + 
			",position = '" + position + '\'' + 
			",goalsAgainst = '" + goalsAgainst + '\'' + 
			",goalDifference = '" + goalDifference + '\'' + 
			",goals = '" + goals + '\'' + 
			"}";
		}
}