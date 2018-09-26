package com.capgemini.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="carts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Carts {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="serial_number")
	private int serialNumber;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="product_id")
	private int productId;
	
	@Column(name="merchant_id")
	private int merchantId;
	
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="total_price")
	private double totalPrice;
	
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Carts(int serialNumber, int quantity, int productId, int merchantId, int customerId) {
		super();
		this.serialNumber = serialNumber;
		this.quantity = quantity;
		this.productId = productId;
		this.merchantId = merchantId;
		this.customerId = customerId;
	}
	public Carts() {
		super();
//		this.serialNumber=-1;
		this.quantity =1;
	}
	@Override
	public String toString() {
		return "Carts [serialNumber=" + serialNumber + ", quantity=" + quantity + ", productId=" + productId
				+ ", merchantId=" + merchantId + ", customerId=" + customerId + ", totalPrice=" + totalPrice + "]";
	}
		
}
