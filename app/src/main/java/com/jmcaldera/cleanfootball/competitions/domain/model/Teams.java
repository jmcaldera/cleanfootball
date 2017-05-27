package com.jmcaldera.cleanfootball.competitions.domain.model;

import com.google.gson.annotations.SerializedName;

public class Teams{

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
			"Teams{" + 
			"href = '" + href + '\'' + 
			"}";
		}
}