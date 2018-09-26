package com.capgemini.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerAddress {
	
	private int customer_ID;
	@Id
	private int address_ID;
	private int pincode;
	private String addressLine;
	private String city;
	private String state;
	
	public int getCustomer_ID() {
		return customer_ID;
	}
	public void setCustomer_ID(int customer_ID) {
		this.customer_ID = customer_ID;
	}
	public int getAddress_ID() {
		return address_ID;
	}
	public void setAddress_ID(int address_ID) {
		this.address_ID = address_ID;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
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
	
	
}
