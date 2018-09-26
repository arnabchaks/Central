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

import com.capgemini.dto.Admin;
import com.capgemini.dto.Carts;
import com.capgemini.dto.Customers;
import com.capgemini.dto.Merchants;
import com.capgemini.dto.ProductCategory;
import com.capgemini.dto.Products;


@Repository
@EnableTransactionManagement
public class DAOLayerImpl implements DAOLayer {

	
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
		String sql="select product from Products product";
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
	@Transactional
	public boolean addProduct(Products product) {
		
		
			em.persist(product);
			return true;
		
	}

	@Override
	public Admin validateAdmin(Admin admin)
	{
		/*Admin check = findById(admin.getAdminId());
		System.out.println(check+"*****returieved from db");
		if (admin.getAdminPswd().equals(check.getAdminPswd()))
			return check;
		return null;*/
		Admin admindummy = new Admin();
		admindummy.setAdminPswd("dummy");
		Query query = em.createQuery("select admin from Admin admin where admin.adminId= :id and admin.adminPswd= :pswd");
		query.setParameter("id", admin.getAdminId());
		query.setParameter("pswd", admin.getAdminPswd());
		List list = query.getResultList();
		if(list.isEmpty())
		{
			return admindummy;
		}
		else
		{
			return admin;
		}
	}
	
	@Override
	public Customers validateCustomerForLogin(Customers customer)
	{
		Customers custdummy = new Customers();
		custdummy.setCustomerPswd("dummy");
		Query query = em.createQuery("select c from Customers c where c.customerEmail= :email and c.customerPswd= :pswd");
		query.setParameter("email", customer.getCustomerEmail());
		query.setParameter("pswd", customer.getCustomerPswd());
		Customers c;
		try
		{
			c = (Customers) query.getSingleResult();
			if(c.equals(null))
				return custdummy;
			else
				return c;
		}
		catch (NoResultException nre)
		{
			
		}
		return custdummy;
	}
	
	@Override
	public Merchants validateMerchantForLogin(Merchants merchant)
	{
		Merchants merdummy = new Merchants();
		merdummy.setMerchantPswd("dummy");
		Query query = em.createQuery("select m from Merchants m where m.merchantEmail= :email and m.merchantPswd= :pswd");
		query.setParameter("email", merchant.getMerchantEmail());
		query.setParameter("pswd", merchant.getMerchantPswd());
		Merchants m;
		try
		{
			m = (Merchants) query.getSingleResult();
			if(m.equals(null))
				return merdummy;
			else
				return m;
		}
		catch (NoResultException nre)
		{
			
		}
		return merdummy;
	}
	
	public Admin findById(int id)
	{
		return em.find(Admin.class, id);
		
	}
	
	@Override
	public Products findProduct(int id) {
		return em.find(Products.class, id);
	}

	@Override
	@Transactional
	public Carts addToCart(Carts cart) {
		int pid = cart.getProductId();
		Products p = em.find(Products.class, pid);
		cart.setTotalPrice(p.getProductPrice());
		em.persist(cart);
		return em.find(Carts.class, cart.getSerialNumber());
	}
	
	@Override
	@Transactional
	public Carts updateQuantity(Integer csn) {
		Carts cart = em.find(Carts.class, csn);
		int q = cart.getQuantity();
		int pid = cart.getProductId();
		Products p = em.find(Products.class, pid);
		cart.setTotalPrice(p.getProductPrice()*q);
		em.merge(cart);
		return cart;
	}
	
	@Override
	@Transactional
	public Carts deleteFromCart(Carts cart) {
		em.remove(cart);
		return cart;
	}

	@Override
	@Transactional
	public Customers createAccount(Customers customer) {
		System.out.println("Entered DAO");
		Customers custdummy = new Customers();
		custdummy.setCustomerPswd("dummy");
		Query query = em.createQuery("select c from Customers c where c.customerEmail= :email or c.customerMobile= :mobile");
		query.setParameter("email", customer.getCustomerEmail());
		query.setParameter("mobile", customer.getCustomerMobile());
		Customers c;
		c = (Customers) query.getSingleResult();
		if(c.equals(null))
		{
			System.out.println("111");
			System.out.println("Query" + (Customers)query.getSingleResult());
			em.persist(customer);
//				return em.find(Customers.class, customer.getCustomerId());
			System.out.println("no result found");
			return c;
		}
		else
		{
			System.out.println(c);
			return custdummy;
		}
	}

	
	 
	@Override
	public Customers findByEmailID(String email_ID) {
		String sql="select customer from Customers customer where customer.customerEmail =:email" ;
		TypedQuery<Customers> tq=em.createQuery(sql,Customers.class);
		tq.setParameter("email", email_ID);
	
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

	/*@Override
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
*/
	@Override
	@Transactional
	public boolean addMerchant(Merchants merchant) {
		
			em.persist(merchant);
			return true;
		
	}
/*
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
	public Feedbacks getFeedBack(Feedbacks feedback) {

		return null;
	}*/

	@Override
	public List<Carts> getCartsById(Integer cid) {
		System.out.println("Inside DAO");
		System.out.println(cid);
		String sql = "select c from Carts c where c.customerId= :cid";
		Query query = em.createQuery(sql);
		query.setParameter("cid", cid);
		System.out.println("After Set Parameter");
		System.out.println(query.getResultList());
		return query.getResultList();
	}


	

/*	public Carts addToCart(int productId, int customerId, int quantity, int merchantId) {
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

	public List<String> selectAddress(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public CustomerOrders retrieveAddress(int customerId, int addressId) {
		// TODO Auto-generated method stub
		return null;
	}

	public CustomerOrders addCoupon(int couponId, int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public CustomerOrders removeCoupon(int couponId, int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public OrderEntries retrieveOrderDetails(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	public CustomerOrders changeStatus(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Invoice generateInvoice(int orderId, int customerId, int transactionId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Invoice makePayment(int amount, int orderId, int customerId) {
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
	public Boolean createAccount(Customers cusDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Products> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean login(String email_ID, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean logout(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String forgotPassword(String email_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public Customers findByCustomerID(int customer_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean changePassword(String email_ID, String password, String newPassword) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
