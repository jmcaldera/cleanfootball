package com.jmcaldera.cleanfootball.competitiondetails.model.standings;

import com.google.gson.annotations.SerializedName;

public class Team{

	@SerializedName("href")
	private String href;

	public void setHref(String href){
		this.href = href;
	}

	public String getHref(){
		return href;
	}

	@Override
 	public String toString(){
		return 
			"Team{" + 
			"href = '" + href + '\'' + 
			"}";
		}
}