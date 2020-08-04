package com.ps.test;

import com.ps.component.ECommerceStore;
import com.ps.factory.ECommerceStoreFactory;

public class Customer1 {

	public static void main(String[] args) {
		ECommerceStore store=null;
		store=ECommerceStoreFactory.getInstance("AM20");
		System.out.println(store.getClass());
		System.out.println("Bill Amount :: "+store.shopping(new String[] {"rakhi","sweets"}, new double[] {4000,3000}));
		

	}

}
