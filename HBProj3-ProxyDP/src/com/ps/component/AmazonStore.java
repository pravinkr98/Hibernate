package com.ps.component;

import java.util.stream.DoubleStream;

public class AmazonStore implements ECommerceStore {

	/*@Override
	public float shopping(String[] items, float[] prices) {
		float total=0.0f;
		for(float p:prices) {
			total +=p;
		}
		return total;
	}*/
	
	/*@Override
	public float shopping(String[] items, float[] prices) {
		double amt[]=new double[prices.length];
		for(int i=0;i<prices.length;i++) {
			amt[i]=prices[i];
		}
		return (float) DoubleStream.of(amt).sum();
	}*/
	
	/*@Override
	public float shopping(String[] items, float[] prices) {
		float amt=0.0f;
		for(int i=0;i<prices.length;i++) {
			amt+=prices[i];
		}
		return amt;
	}*/
	
	
	@Override
	public double shopping(String[] items, double[] prices) {
		return DoubleStream.of(prices).sum();
	}

}
