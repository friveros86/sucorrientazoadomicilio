package com.sucorrientazoadomicilio.domain;

import java.util.List;

public class Delivery {

	private List<Address> addressList;
	private String filename;

	public Delivery(List<Address> addressList, String filename) {
		super();
		this.addressList = addressList;
		this.filename = filename;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
	
}
