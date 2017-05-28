package com.jmcaldera.cleanfootball.competitiondetails.model.standings;

import com.google.gson.annotations.SerializedName;

public class Competition{

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
			"Competition{" + 
			"href = '" + href + '\'' + 
			"}";
		}
}