package com.capgemini.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="customers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customers {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="customer_id")
	private int customerId;
	@Column(name="customer_name")
	@NotNull(message="This field can't be empty")
	private String customerName;
	@Column(name="customer_email")
	@Email
	private String customerEmail;
	@Column(name="customer_pswd")
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})")
	private String customerPswd;
	@Column(name="customer_mobile")
	@Size(min=10, max=10)
	private String customerMobile;
	@Column(name="customer_flag")
	private boolean customerFlag;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerPswd() {
		return customerPswd;
	}
	public void setCustomerPswd(String customerPswd) {
		this.customerPswd = customerPswd;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	public boolean isCustomerFlag() {
		return customerFlag;
	}
	public void setCustomerFlag(boolean customerFlag) {
		this.customerFlag = customerFlag;
	}

	@Override
	public String toString() {
		return "Customers [customerId=" + customerId + ", customerName=" + customerName + ", customerEmail="
				+ customerEmail + ", customerPswd=" + customerPswd + ", customerMobile=" + customerMobile
				+ ", customerFlag=" + customerFlag + "]";
	}
}
