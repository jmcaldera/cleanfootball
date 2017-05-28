package com.jmcaldera.cleanfootball.competitiondetails.model.standings;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Standing{

	@SerializedName("leagueCaption")
	private String leagueCaption;

	@SerializedName("standing")
	private List<StandingItem> standing;

	@SerializedName("_links")
	private Links links;

	@SerializedName("matchday")
	private int matchday;

	public void setLeagueCaption(String leagueCaption){
		this.leagueCaption = leagueCaption;
	}

	public String getLeagueCaption(){
		return leagueCaption;
	}

	public void setStanding(List<StandingItem> standing){
		this.standing = standing;
	}

	public List<StandingItem> getStanding(){
		return standing;
	}

	public void setLinks(Links links){
		this.links = links;
	}

	public Links getLinks(){
		return links;
	}

	public void setMatchday(int matchday){
		this.matchday = matchday;
	}

	public int getMatchday(){
		return matchday;
	}

	public Standing(String leagueCaption, int matchday, List<StandingItem> standing) {
		this.leagueCaption = leagueCaption;
		this.matchday = matchday;
		this.standing = standing;
	}

	@Override
 	public String toString(){
		return 
			"Standing{" + 
			"leagueCaption = '" + leagueCaption + '\'' + 
			",standing = '" + standing + '\'' + 
			",_links = '" + links + '\'' + 
			",matchday = '" + matchday + '\'' + 
			"}";
		}
}