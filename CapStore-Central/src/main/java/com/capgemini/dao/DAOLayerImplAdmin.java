package com.capgemini.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dto.Admin;
import com.capgemini.dto.Carts;
import com.capgemini.dto.CustomerOrders;
import com.capgemini.dto.Customers;
import com.capgemini.dto.Feedbacks;
import com.capgemini.dto.Invoice;
import com.capgemini.dto.Merchants;
import com.capgemini.dto.OrderEntries;
import com.capgemini.dto.Pincodes;
import com.capgemini.dto.ProductCategory;
import com.capgemini.dto.Products;
import com.capgemini.dto.WishList;
//import com.capgemini.exception.CapStoreException;
import com.capgemini.exception.CapStoreException;


@Repository
@EnableTransactionManagement
public class DAOLayerImplAdmin implements DAOLayerAdmin {

	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Merchants> getAllMerchant() {
		String sql="select merchant from Merchants merchant";
		javax.persistence.TypedQuery<Merchants> tq=em.createQuery(sql, Merchants.class);
		List<Merchants> lm=tq.getResultList();
		return lm;
	}

	@Override
	public List<Customers> getAllCustomer() {
		String sql="select customer from Customer customer";
		TypedQuery<Customers> tq=em.createQuery(sql,Customers.class);
		List<Customers> lm=tq.getResultList();
		return lm;
	}

	@Override
	public List<Products> getAllProduct() {
		String sql="select product from Product product";
		TypedQuery<Products> tq=em.createQuery(sql,Products.class);
		List<Products> lm=tq.getResultList();
		return lm;
	}

	@Override
	public List<Products> getAllProduct(int merchantId) {
		String sql="select product from Product product join product.merchants merchants where merchants.merchant_id in (:id) ";
		TypedQuery<Products> tq=em.createQuery(sql,Products.class);
		tq.setParameter("id", merchantId);
		List<Products> lm=tq.getResultList();
		return lm;
	}
	

	

	@Override
	public Admin validateAdmin(Admin admin) {

		System.out.println(admin.getAdminId() + "***" + admin.getAdminPswd());
		Admin check = findById(admin.getAdminId());
		System.out.println(check+"*****returieved from db");
		if (admin.getAdminPswd().equals(check.getAdminPswd()))
			return check;
		return null;
	}
	
	
	public Admin findById(int id)
	{
		return em.find(Admin.class, id);
		
	}

	
	
	@Override
	public Products deleteProduct(Products product) {
		em.remove(product);
		return product;
	}
	@Override
	public Products updateProduct(Products product) {
		Products p=em.find(Products.class, product.getProductId());
		//pending
		return p;
	}

	@Override
	@Transactional
	public boolean addMerchant(Merchants merchant) {
		
			em.persist(merchant);
			return true;
		
	}

	@Override
	public Merchants deleteMerchant(Merchants merchant) {
		em.remove(merchant);
		return merchant;
	}
	@Override
	public Merchants updateMerchant(Merchants merchant) {
		Merchants m=em.find(Merchants.class,merchant.getMerchantId());
		//pending
		return m;
	}
	@Override
	public boolean checkAvailabilty(Products product) {
		Products p=em.find(Products.class,product.getProductId());
		if(p.getProductQuantity()-1<0)
			return true;
		else
			return false;
	}
	@Override
	public boolean checkDeliveryAvailability(Products product, int pincode) throws CapStoreException {
		
		String sql="select pincode from mp from MerchantPincode mp where merchant_id in"
				+ "(select merchants.merchant_id from  Product p join p.merchants merchants where p.product_id in (:id))";
		
		TypedQuery<Integer> tq=em.createQuery(sql,Integer.class);
		tq.setParameter("id", product.getProductId());
		List<Integer> pin=tq.getResultList();
		for(Integer p:pin){
			if(p.equals(pincode))
				return true;
		}
		
		return false;
	}
	

	@Override
	public List<String> selectAddress(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerOrders retrieveAddress(int customerId, int addressId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerOrders addCoupon(int couponId, int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerOrders removeCoupon(int couponId, int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderEntries retrieveOrderDetails(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerOrders changeStatus(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invoice generateInvoice(int orderId, int customerId, int transactionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invoice makePayment(int amount, int orderId, int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	/*@Override
	public Boolean logout(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}*/
	@Override
	public Customers findByCustomerIDforAdmin(int customer_ID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}