package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.dto.AddressList;
import com.capgemini.dto.CustomerOrders;
import com.capgemini.dto.Customers;
import com.capgemini.dto.OrderList;
import com.capgemini.dto.ProductCategory;
import com.capgemini.dto.ProductListDummy;
import com.capgemini.dto.Products;
import com.capgemini.exception.CapStoreException;
import com.capgemini.service.ServiceLayer;

@RestController
public class RestControllerCustomer {
	@Autowired
	ServiceLayer ser;

	@RequestMapping(value = "/loginCustomer", method = RequestMethod.POST)
	public Customers loginCustomer(@RequestBody Customers customer) throws CapStoreException {

		customer = ser.validateCustomerForLogin(customer);
		return customer;

	}

	@RequestMapping(value = "/productList", method = RequestMethod.GET)
	public ProductListDummy displayProductList(String category, String search) {
		ProductListDummy p = new ProductListDummy();
		
		p.setProducts(ser.customerSearch(search, category));

		return p;
	}
	
	@RequestMapping(value = "/pdetails", method = RequestMethod.GET)
	public Products productById(Integer id) {

		return ser.findPById(id);
	}

	@RequestMapping(value = "/signUpCustomer", method = RequestMethod.POST)
	public Customers signUp(@RequestBody Customers cust) {

		System.out.println("*-*gd insde controller of central SIgn Up");

		cust = ser.createAccount(cust);

		System.out.println("after all validation in central****!!!!!");

		System.out.println(cust.getCustomerName());

		// return ;
		return cust;
	}

	@RequestMapping("/addresses")
	public AddressList dispAddressList(Integer cust) {
		System.out.println("Something" + cust);
		AddressList ad = new AddressList();
		System.out.println("ghkgj");
		System.out.println("hhh" + ser.getAddresses(cust));
		ad.setAdd(ser.getAddresses(cust));
		System.out.println("Something new");
		return ad;
	}

	@RequestMapping("/findById")
	public Customers findbyid(Integer id) {
		Customers c = ser.findCById(id);
		return c;
	}

	@RequestMapping("/orders")
	public OrderList dispOrderList(Integer cust) {
		System.out.println("Something" + cust);
		OrderList ad = new OrderList();
		ad.setAdd(ser.getOrders(cust));
		return ad;
	}

	/*
	 * @RequestMapping(value="/quote") public Quote consumeQuote(){ RestTemplate
	 * restTemplate = new RestTemplate(); Quote quote =
	 * restTemplate.getForObject(
	 * "http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
	 * 
	 * return quote; }
	 * 
	 * @RequestMapping(value="/message") public Message consumeMessage(){
	 * System.out.println("******-*-*-*"); RestTemplate restTemplate = new
	 * RestTemplate(); Message message =
	 * restTemplate.getForObject("http://localhost:9090/getmessage?id=1",
	 * Message.class);
	 * 
	 * 
	 * return message; }
	 * 
	 * @RequestMapping(value="/send") public Message sendMessage(){ Message m =
	 * new Message(); m.setText("Java client Posting message"); m.setSender(new
	 * Sender("Java Client")); System.out.println(m); RestTemplate restTemplate
	 * = new RestTemplate(); Message message =
	 * restTemplate.postForObject("http://localhost:9090/receive",m,
	 * Message.class);
	 * 
	 * return message; }
	 */

}