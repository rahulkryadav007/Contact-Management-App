package com.contact.entity;

public class contacts {

	private String name;
	private String adress;
	private String sim;
	private long  number;
	public contacts() {
		super();
		// TODO Auto-generated constructor stub
	}
	public contacts(String name, String adress, String sim, long number) {
		super();
		this.name = name;
		this.adress = adress;
		this.sim = sim;
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getSim() {
		return sim;
	}
	public void setSim(String sim) {
		this.sim = sim;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	
	
	
	
}
