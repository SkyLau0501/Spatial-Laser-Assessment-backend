package com.takehome.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "table_B")
public class TableB {

	@Id
	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	public TableB() {

	}
	
	public TableB(String address, String city, String state) {
		this.address = address;
		this.city = city;
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "TableA [address=" + address + ", city=" + city + ", state=" + state + "]";
	}

	
}