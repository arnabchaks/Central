package com.capgemini.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.DAOLayer;
import com.capgemini.dao.DAOLayerAdmin;
import com.capgemini.dao.DAOLayerCustomer;
import com.capgemini.dao.DAOLayerMerchant;
import com.capgemini.dto.Admin;
import com.capgemini.dto.Carts;
import com.capgemini.dto.CustomerAddress;
import com.capgemini.dto.CustomerOrders;
import com.capgemini.dto.Customers;
import com.capgemini.dto.Merchants;
import com.capgemini.dto.ProductCategory;
import com.capgemini.dto.Products;
import com.capgemini.exception.CapStoreException;

@Service
public class ServiceLayerImpl implements ServiceLayer
{
	@Autowired
	private DAOLayer daoref;
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private DAOLayerAdmin daorefAdmin;
	@Autowired
	private DAOLayerCustomer daorefCustomer;
	@Autowired
	private DAOLayerMerchant daorefMerchant;
	@Override
	public Admin validateAdmin(Admin admin)
	{
		return daoref.validateAdmin(admin);
	}

	
	@Override
	public Admin findById(int id) {
		return daoref.findById(id);
	}


	@Override
	public Customers createAccount(Customers cusDTO) {
		return daoref.createAccount(cusDTO);
	}


	@Override
	public Customers validateCustomerForLogin(Customers customer) throws CapStoreException {
		return daorefCustomer.validateCustomerForLogin(customer);
	}


	@Override
	public String check() {
		return daoref.check();
	}

	@Override
	public List<Products> findAll() {
		String sql="select product from Products product";
		TypedQuery<Products> tq=em.createQuery(sql,Products.class);
		List<Products> lm=tq.getResultList();
		return lm;
	}


	@Override
	public List<CustomerAddress> getAddresses(int customerId) {
		System.out.println("Service");
		return daorefCustomer.getAddresses(customerId);
	}
	@Override
	public Customers findCById(int id) {
		return daorefCustomer.findByID(id);
	}
	
	@Override
	public Products findPById(int id) {
		String sql="select pd from Products pd where pd.productId =:id" ;
		TypedQuery<Products> tq=em.createQuery(sql,Products.class);
		tq.setParameter("id", id);
		
		return tq.getSingleResult();
	}

	@Override
	public List<Products> findByCategory(Enum cat) {
		
		String sql="select product from Products product where productCategory=:cat";
		TypedQuery<Products> tq=em.createQuery(sql,Products.class);
		tq.setParameter("cat", cat);
		List<Products> lm=tq.getResultList();
		return lm;
	}


	@Override
	public List<CustomerOrders> getOrders(Integer cust) {
		
		return daorefCustomer.getOrders(cust);
	}

	@Override
	public List<Products> customerSearch(String productName, String cat) {
		
		Query query;
		
		if(cat.equals("all")) {
			query = em.createQuery("Select p from Products p");
		}
		else {
			query = em.createQuery("Select p from Products p where productCategory = ?1");
			ProductCategory category = ProductCategory.valueOf(cat.toUpperCase());
			query.setParameter(1, category);
		}
		
		List<Products> searchList = query.getResultList();
		
		if(productName.equals(""))
			return searchList;
		
		for (int i = 0; i < searchList.size(); i++){
			Products p = searchList.get(i);
			
			int flag=0;
			String name = p.getProductName();
			String nameArr[] = name.split(" ");
			String queryArray[] = productName.split(" ");
			for(String iter: nameArr){
				for(String iter2: queryArray){
					if(iter.equals(iter2)){
						flag++;
						System.out.println("name "+iter+" search "+iter2);
						break;
					}
				}
			}
			System.out.println("flag "+flag+"prod "+p);
			if(flag==0) searchList.remove(p);
		}

		System.out.println(searchList);
		Collections.sort(searchList, new Comparator<Products>(){
			
			public int compare(Products o1, Products o2) {
				if((o1.getProductName().contains(productName))&&(o2.getProductName().contains(productName))){
					return o2.getProductName().length() - o1.getProductName().length();
				}
				else if((o1.getProductName().contains(productName))&&(!o2.getProductName().contains(productName))){
					return 1;
				}
				else if((!o1.getProductName().contains(productName))&&(o2.getProductName().contains(productName))){
					return -1;
				}
				else if((!o1.getProductName().contains(productName))&&(!o2.getProductName().contains(productName))){
					int flag1 = 0;
					int flag2 = 0;
					String name1 = o1.getProductName();
					String name2 = o2.getProductName();
					String name1Arr[] = name1.split(" ");
					String name2Arr[] = name2.split(" ");
					String queryArray[] = productName.split(" ");
					for(String iter: name1Arr){
						for(String iter2: queryArray){
							if(iter.equals(iter2)){
								flag1++;
							}
						}
					}
					
					for(String iter: name2Arr){
						for(String iter2: queryArray){
							if(iter.equals(iter2)){
								flag2++;
							}
						}
					}
					if(flag1>flag2) return 1;
					else return -1;
				}
				else return 1;
			}
		
		});
		
		return searchList;
	}
	
	@Override
	public List<Products> getAllProduct() {
		return daoref.getAllProduct();
	}


	@Override
	public Merchants validateMerchantForLogin(Merchants merchant) {
		return daoref.validateMerchantForLogin(merchant);
	}


	@Override
	public Products findProduct(int id) {
		return daoref.findProduct(id);
	}


	@Override
	public Carts addToCart(Carts cart) {
		return daoref.addToCart(cart);
	}


	@Override
	public Carts updateQuantity(Integer csn) {
		return daoref.updateQuantity(csn);
	}


	@Override
	public Carts deleteFromCart(Carts cart) {
		return daoref.deleteFromCart(cart);
	}


	@Override
	public List<Carts> getCartsById(Integer cid) {
		return daoref.getCartsById(cid);
	}
}
