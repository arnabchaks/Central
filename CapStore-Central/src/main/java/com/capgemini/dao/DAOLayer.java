package com.capgemini.dao;
//Test
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Repository;

//import com.capgemini.exception.CapStoreException;
import com.capgemini.dto.*;
@Repository
public interface DAOLayer {
	// Cart Operations
/*		public Carts addToCart(int productId, int customerId, int quantity, int merchantId);
		public Carts removeFromCart(int productId, int customerId);
		public Carts emptyCart(int cartId);
		public Carts saveCart(int cartId);
		public Carts retrieveCart(int customerId);
		
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
		
		// WishList Operations
		public WishList addToWishList(int productId, int customerId, int merchantId);
		public WishList removeFromWishList(int wishlistId);
		public WishList emptyWishList(int wishlistId);
		public WishList saveWishList(int wishlistId);
		public WishList retrieveWishList(int customerId);
	
	*******public Boolean createAccount(Customers cusDTO);

	public List<Products> findAll();

	*******public Boolean login(String email_ID, String password);

	*******public Boolean logout(HttpServletRequest request);

	public String forgotPassword(String email_ID);

	

	public Customers findByCustomerID(int customer_ID);

	public Boolean changePassword(String email_ID, String password, String newPassword);*/
	
	public Customers createAccount(Customers cusDTO);
	public Admin findById(int id);
	public Admin validateAdmin(Admin admin);
	public List<Merchants> getAllMerchant();
	public List<Customers> getAllCustomer();
	public List<Products> getAllProduct();
	public List<Products> getAllProduct(int merchantId);
	public boolean addProduct(Products product);
	/*public Products deleteProduct(Products product) throws CapStoreException;
	public Products updateProduct(Products product) throws CapStoreException;
	public boolean addMerchant(Merchants merchant);
	public Merchants deleteMerchant(Merchants merchant) throws CapStoreException;
	public Merchants updateMerchant(Merchants merchant) throws CapStoreException;
	public boolean checkAvailabilty(Products product) throws CapStoreException;
	public boolean checkDeliveryAvailability(Products product, int pincode) throws CapStoreException;
	public Feedbacks getFeedBack(Feedbacks feedback);
	*/
	public Customers validateCustomerForLogin(Customers customer);
	public Customers findByEmailID(String email_ID);
	public String check();
	boolean addMerchant(Merchants merchant);
	public Merchants validateMerchantForLogin(Merchants merchant);
	public Products findProduct(int id);
	public Carts addToCart(Carts cart);
	public Carts updateQuantity(Integer csn);
	public Carts deleteFromCart(Carts cart);
	public List<Carts> getCartsById(Integer cid);
	
	
	
	
	
	
}
