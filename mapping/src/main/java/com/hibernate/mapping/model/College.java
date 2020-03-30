package com.hibernate.mapping.model;

import javax.persistence.Embeddable;

@Embeddable
public class College {
	
	private int clgId;
		
	private String clgName;
		
	private String clgLocation;
	
	public College() {
		
	}
	
	public College(int clgId, String clgName, String clgLocation) {
		this.clgId = clgId;
		this.clgName = clgName;
		this.clgLocation = clgLocation;
	}

	public int getClgId() {
		return clgId;
	}

	public void setClgId(int clgId) {
		this.clgId = clgId;
	}

	public String getClgName() {
		return clgName;
	}

	public void setClgName(String clgName) {
		this.clgName = clgName;
	}

	public String getClgLocation() {
		return clgLocation;
	}

	public void setClgLocation(String clgLocation) {
		this.clgLocation = clgLocation;
	}
	

}
