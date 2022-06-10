package com.ssafy.vo;

public class Member {
	private String id, pwd, name, birthDate, address;

	public Member() {
	}

	public Member(String id, String pwd, String name, String birthDate, String address) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birthDate = birthDate;
		this.address = address;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
	
}
