package com.capgemini.dto;

import java.util.ArrayList;
import java.util.List;

public class AddressList {

	List<CustomerAddress> add;
	
	public List<CustomerAddress> getAdd() {
		return add;
	}

	public void setAdd(List<CustomerAddress> add) {
		this.add = add;
	}

	public AddressList() {
		this.add = new ArrayList<CustomerAddress>();
	}

	
}
