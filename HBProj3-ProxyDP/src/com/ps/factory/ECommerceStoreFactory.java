package com.ps.factory;

import com.ps.component.AmazonStore;
import com.ps.component.ECommerceStore;
import com.ps.proxy.ECommerceStoreDiscountProxy;

public class ECommerceStoreFactory {
	
	public static ECommerceStore getInstance(String cuponCode) {
		
		if(cuponCode.equalsIgnoreCase("") || cuponCode.trim().length()==0) {
			return new AmazonStore(); //real object
		}
		else{
			if(cuponCode.equalsIgnoreCase("AM10"))
				return new ECommerceStoreDiscountProxy(10);
			else if(cuponCode.equalsIgnoreCase("AM20"))
				return new ECommerceStoreDiscountProxy(20);
			else
				return new ECommerceStoreDiscountProxy(5);
		}
	}

}
