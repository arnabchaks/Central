package com.capgemini.dto;

import java.util.ArrayList;
import java.util.List;

public class CartListDummy
{
    private List<Carts> carts;
 
    public CartListDummy()
    {
    	carts = new ArrayList<>();
    }

	public List<Carts> getCarts() {
		return carts;
	}

	public void setCarts(List<Carts> carts) {
		this.carts = carts;
	}

	
}
