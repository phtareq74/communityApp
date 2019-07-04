package com.ourCommuinty.communityWebApp;

import javax.persistence.Id;

public class Member {
	
	@Id

	private long id;
	private String name;
	private int age;
	private String email;
	private String job;
	
	public Member(long id, String name, int age, String email, String job) {
		this.id = id;
		this.name= name;
		this.age = age;
		this.email = email;
		this.job = job;
	}
	public long getId() {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge(){
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	
}
