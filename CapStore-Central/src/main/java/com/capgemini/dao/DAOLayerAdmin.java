package com.capgemini.dao;
//TestRaju
import java.util.List;

import org.springframework.stereotype.Repository;

//import com.capgemini.exception.CapStoreException;
import com.capgemini.dto.Admin;
import com.capgemini.dto.CustomerOrders;
import com.capgemini.dto.Customers;
import com.capgemini.dto.Invoice;
import com.capgemini.dto.Merchants;
import com.capgemini.dto.OrderEntries;
import com.capgemini.dto.Products;
import com.capgemini.exception.CapStoreException;
@Repository
public interface DAOLayerAdmin {
	
		// Place Order Operations
		public List<String> selectAddress(int customerId);
		public CustomerOrders retrieveAddress(int customerId, int addressId);
		
		public CustomerOrders addCoupon(int couponId, int customerId);
		public CustomerOrders removeCoupon(int couponId, int customerId);
		
		public OrderEntries retrieveOrderDetails(int orderId);
		public CustomerOrders changeStatus(int customerId);
		
		// Payment and Invoice Operations
		public Invoice generateInvoice(int orderId, int customerId, int transactionId);
		public Invoice makePayment(int amount, int orderId, int customerId);
		
		//public Boolean logout(HttpServletRequest request);
	
		public Admin findById(int id);
		public Admin validateAdmin(Admin admin);
		public List<Merchants> getAllMerchant();
		public List<Customers> getAllCustomer();
		public List<Products> getAllProduct();
		public List<Products> getAllProduct(int merchantId);
	
	public Products deleteProduct(Products product) throws CapStoreException;
	public Products updateProduct(Products product) throws CapStoreException;
	public boolean addMerchant(Merchants merchant);
	public Merchants deleteMerchant(Merchants merchant) throws CapStoreException;
	public Merchants updateMerchant(Merchants merchant) throws CapStoreException;
	public boolean checkAvailabilty(Products product) throws CapStoreException;
	public boolean checkDeliveryAvailability(Products product, int pincode) throws CapStoreException;
	Customers findByCustomerIDforAdmin(int customer_ID);
	
	
	
	
		
	
	
	
	
	
	
}