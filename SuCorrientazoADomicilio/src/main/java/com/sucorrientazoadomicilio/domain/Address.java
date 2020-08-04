package com.sucorrientazoadomicilio.domain;

import java.util.List;

public class Address {

	private List<Movements> movementsList;

	
	public Address() {
		super();
	}

	public Address(List<Movements> movementsList) {
		super();
		this.movementsList = movementsList;
	}

	public List<Movements> getMovementsList() {
		return movementsList;
	}

	public void setMovementsList(List<Movements> movementsList) {
		this.movementsList = movementsList;
	}
	
}
