package com.sucorrientazoadomicilio.domain;

import java.util.List;

public class Delivery {

	private List<Address> addressList;

	public Delivery(List<Address> addressList) {
		super();
		this.addressList = addressList;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

}
