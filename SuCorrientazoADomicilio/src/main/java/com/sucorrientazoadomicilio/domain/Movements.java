package com.sucorrientazoadomicilio.domain;

public enum Movements {

	A("A"),D("D"),I("I");
	
	private String description;

	
	 Movements(String description) {
		this.description = description;
	}


	public String getDescription() {
		return description;
	}

	
	
}
