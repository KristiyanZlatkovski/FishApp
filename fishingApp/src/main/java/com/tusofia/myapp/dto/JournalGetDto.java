package com.tusofia.myapp.dto;

import java.util.Date;

public class JournalGetDto {

	  private Long id;
	  private String userName;
	  private String waterName;
	  private String fishName;
	  private String baitName;
	  private Date date;
	  private String path;
	  private double size;
	  private double weight;
	  private String info;
	  
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public String getWaterName() {
		return waterName;
	}
	public void setWaterName(String waterName) {
		this.waterName = waterName;
	}
	public String getFishName() {
		return fishName;
	}
	public void setFishName(String fishName) {
		this.fishName = fishName;
	}
	public String getBaitName() {
		return baitName;
	}
	public void setBaitName(String baitName) {
		this.baitName = baitName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
