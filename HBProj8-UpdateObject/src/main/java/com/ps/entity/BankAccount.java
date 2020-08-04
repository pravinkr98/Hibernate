package com.ps.entity;

public class BankAccount {

	private long acno;
	private String holderName;
	private double balance;
	
	public long getAcno() {
		return acno;
	}
	public void setAcno(long acno) {
		this.acno = acno;
	}
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "BankAccount [acno=" + acno + ", holderName=" + holderName + ", balance=" + balance + "]";
	}	
	
}
