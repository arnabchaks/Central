package com.capgemini.dao;

import java.util.List;

import com.capgemini.dto.CustomerAddress;
import com.capgemini.dto.CustomerOrders;
import com.capgemini.dto.Customers;
import com.capgemini.dto.Feedbacks;
import com.capgemini.dto.Products;
import com.capgemini.dto.WishList;
import com.capgemini.exception.CapStoreException;

public interface DAOLayerCustomer {
	// Cart Operations
	/*		public Carts addToCart(int productId, int customerId, int quantity, int merchantId);
			public Carts removeFromCart(int productId, int customerId);
			public Carts emptyCart(int cartId);
			public Carts saveCart(int cartId);
			public Carts retrieveCart(int customerId);
			*/
	// WishList Operations
			public WishList addToWishList(int productId, int customerId, int merchantId);
			public WishList removeFromWishList(int wishlistId);
			public WishList emptyWishList(int wishlistId);
			public WishList saveWishList(int wishlistId);
			public WishList retrieveWishList(int customerId);
			
			public Customers createAccount(Customers cusDTO);
			public Boolean changePassword(String email_ID, String password, String newPassword);
			public Customers validateCustomerForLogin(Customers customer) throws CapStoreException;
			public List<Products> findAll();
			public String forgotPassword(String email_ID);
			
			public Feedbacks getFeedBack(Feedbacks feedback);
			public Customers findByEmailID(String email_ID) throws CapStoreException;
			public String check();
			List<CustomerAddress> getAddresses(int customerId);
			public Customers findByID(int ID);
			List<CustomerOrders> getOrders(int customerId);
}
