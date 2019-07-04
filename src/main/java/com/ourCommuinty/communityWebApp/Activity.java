package com.ourCommuinty.communityWebApp;

public class Activity {
	private int id;
	private String name;
    private String place;
    private String date;
	public Activity(int id, String name, String place, String date) {
		super();
		this.id = id;
		this.name = name;
		this.place = place;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
    
}
