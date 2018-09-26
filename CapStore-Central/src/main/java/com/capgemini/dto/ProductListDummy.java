package com.capgemini.dto;

import java.util.ArrayList;
import java.util.List;

public class ProductListDummy
{
    private List<Products> products;
 
    public ProductListDummy()
    {
        products = new ArrayList<>();
    }
	
    public List<Products> getProducts()
    {
		return products;
	}

	public void setProducts(List<Products> products)
	{
			this.products = products;
	}
}
