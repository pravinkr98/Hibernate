package com.ps.proxy;

import com.ps.component.AmazonStore;
import com.ps.component.ECommerceStore;

public class ECommerceStoreDiscountProxy implements ECommerceStore {
	private float discount;
	private ECommerceStore real;
	
	public ECommerceStoreDiscountProxy(float discount) {
		this.discount = discount;
		real=new AmazonStore();
	}
	
	@Override
	public double shopping(String[] items, double[] prices) {
		double billAmt=0.0;
		//use real object
		billAmt=real.shopping(items, prices);
		//give and return the discounted amount
		return billAmt-(billAmt*discount/100);				
	}

}
