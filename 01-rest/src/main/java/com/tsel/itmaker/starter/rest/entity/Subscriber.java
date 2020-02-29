package com.tsel.itmaker.starter.rest.entity;

public class Subscriber {
    private long msisdn;
    private String type;
    private long balance;

    public Subscriber(long msisdn, String type, long balance) {
        this.msisdn = msisdn;
        this.type = type;
        this.balance = balance;
	}

	public long getMsisdn() {
        return msisdn;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(long msisdn) {
        this.msisdn = msisdn;
    }
    

}
