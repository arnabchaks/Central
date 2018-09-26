package com.capgemini.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dto.Carts;
import com.capgemini.dto.CustomerAddress;
import com.capgemini.dto.CustomerOrders;
import com.capgemini.dto.Customers;
import com.capgemini.dto.Feedbacks;
import com.capgemini.dto.Merchants;
import com.capgemini.dto.ProductCategory;
import com.capgemini.dto.Products;
import com.capgemini.dto.WishList;
import com.capgemini.exception.CapStoreException;

@Repository
@EnableTransactionManagement
public class DAOLayerImplCustomer implements DAOLayerCustomer {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public Customers createAccount(Customers cusDTO) {
		em.persist(cusDTO);
		return em.find(Customers.class, cusDTO.getCustomerId());
	}
	
	@Override
	@Transactional
	public List<CustomerAddress> getAddresses(int customerId) {
		System.out.println("Entered");
		int a = customerId;
		System.out.println("a : " + a);
//		String sql="select add from CustomerAddress add where customer_ID=1";
		String sql="select add from CustomerAddress add where customer_ID= ?1";
//		TypedQuery<CustomerAddress> tq=em.createQuery(sql,CustomerAddress.class);
		System.out.println("2");
		Query query = em.createQuery(sql);
		query.setParameter(1, a);
//		query.setParameter("customerId", (int)customerId);
		List<CustomerAddress> lm = query.getResultList();
		System.out.println(lm);
		return lm;
	}

	@Override
	public Customers validateCustomerForLogin(Customers customer) throws CapStoreException {
		Customers check=findByEmailID(customer.getCustomerEmail());
		if(check.getCustomerPswd().equals(customer.getCustomerPswd()))
			return check;
		else
			throw new CapStoreException("Password doesnt match");
	}
	
	@Override
	@Transactional
	public List<CustomerOrders> getOrders(int customerId) {
		System.out.println("Entered");
		int a = customerId;
		System.out.println("a : " + a);
//		String sql="select add from CustomerAddress add where customer_ID=1";
		String sql="select order from CustomerOrders order where customer_ID= ?1";
//		TypedQuery<CustomerAddress> tq=em.createQuery(sql,CustomerAddress.class);
		System.out.println("2");
		Query query = em.createQuery(sql);
		query.setParameter(1, a);
//		query.setParameter("customerId", (int)customerId);
		List<CustomerOrders> lm = query.getResultList();
		System.out.println(lm);
		return lm;
	}



	
	@Override
	public Customers findByEmailID(String email_ID) throws CapStoreException {
		String sql="select customer from Customers customer where customer.customerEmail =:email" ;
		TypedQuery<Customers> tq=em.createQuery(sql,Customers.class);
		tq.setParameter("email", email_ID);
		try {
			return tq.getSingleResult();
		}
		catch (NoResultException exc) {
			throw new CapStoreException("Account not found");
		}
	}
	@Override
	public Customers findByID(int ID) {
		String sql="select customer from Customers customer where customer.customerId =:ID" ;
		TypedQuery<Customers> tq=em.createQuery(sql,Customers.class);
		tq.setParameter("ID", ID);
	
		return tq.getSingleResult();
		
	}
	@Override
	@Transactional
	public String check() {
		
		Products p=new Products();
		p.setProductCategory(ProductCategory.ELECTRONICS);
		//p.setProductId(103);
		p.setProductName("PocoF1");
		p.setProductPrice(25000);
		p.setProductQuantity(25);
		p.setProductRating(4);
		p.setTotalSold(10);
		Merchants m=new Merchants();
		m.setMerchantAddress("Delhi");
		m.setMerchantEmail("m2@gmail.com");
		m.setMerchantFlag(true);
		//m.setMerchantId(202);
		m.setMerchantMobile("7894561235");
		m.setMerchantName("A1Sales");
		m.setMerchantPswd("m2abcd");
		m.setMerchantRating(3);
		m.setMerchanttype("seller");
		List<Merchants> l1 = new ArrayList<>();
		l1.add(m);
		p.setMerchants(l1);
		em.persist(m);
		em.persist(p);
		return "hello";
		
		
		
	}
	@Override
	public Feedbacks getFeedBack(Feedbacks feedback) {
		return null;
	}
	@Override
	public List<Products> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	public Carts addToCart(int productId, int customerId, int quantity, int merchantId) {
		// TODO Auto-generated method stub
		return null;
	}
	public Carts removeFromCart(int productId, int customerId) {
		// TODO Auto-generated method stub
		return null;
	}
	public Carts emptyCart(int cartId) {
		int i=1;
		return null;
	}
	public Carts saveCart(int cartId) {
		// TODO Auto-generated method stub
		return null;
	}
	public Carts retrieveCart(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}
	public WishList addToWishList(int productId, int customerId, int merchantId) {
		// TODO Auto-generated method stub
		return null;
	}
	public WishList removeFromWishList(int wishlistId) {
		// TODO Auto-generated method stub
		return null;
	}
	public WishList emptyWishList(int wishlistId) {
		// TODO Auto-generated method stub
		return null;
	}
	public WishList saveWishList(int wishlistId) {
		// TODO Auto-generated method stub
		return null;
	}
	public WishList retrieveWishList(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String forgotPassword(String email_ID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Boolean changePassword(String email_ID, String password, String newPassword) {
		// TODO Auto-generated method stub
		return null;
	}
}
