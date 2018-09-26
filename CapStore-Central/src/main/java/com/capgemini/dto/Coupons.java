package com.capgemini.dto;

import java.util.Date;

public class Coupons {
	private int couponId;
	private String couponCode;
	private Date couponDate;
	private String couponDuration;
	private int couponAmount;
	public int getCouponId() {
		return couponId;
	}
	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public Date getCouponDate() {
		return couponDate;
	}
	public void setCouponDate(Date couponDate) {
		this.couponDate = couponDate;
	}
	public String getCouponDuration() {
		return couponDuration;
	}
	public void setCouponDuration(String couponDuration) {
		this.couponDuration = couponDuration;
	}
	public int getCouponAmount() {
		return couponAmount;
	}
	public void setCouponAmount(int couponAmount) {
		this.couponAmount = couponAmount;
	}

}
