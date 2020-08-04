package com.sucorrientazoadomicilio.domain;

public enum CardinalPonits {

	NORTH("N"), SOUTH("S"), WEST("W"), EAST("E");

	private final String value;

	CardinalPonits(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
