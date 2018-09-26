package com.capgemini.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderList {

	List<CustomerOrders> add;
	
	public List<CustomerOrders> getAdd() {
		return add;
	}

	public void setAdd(List<CustomerOrders> add) {
		this.add = add;
	}

	public OrderList() {
		this.add = new ArrayList<CustomerOrders>();
	}

	@Override
	public String toString() {
		return "OrderList [add=" + add + "]";
	}

	

	
}