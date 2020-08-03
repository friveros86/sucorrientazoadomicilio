package com.sucorrientazoadomicilio.domain;

public class Position {

	private int x;
	private int y;
	private CardinalPonits cardinalPoint;

	public Position() {
		super();
	}

	public Position(int x, int y, CardinalPonits cardinalPoint) {
		super();
		this.x = 0;
		this.y = 0;
		this.cardinalPoint = cardinalPoint;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public CardinalPonits getCardinalPoint() {
		return cardinalPoint;
	}

	public void setCardinalPoint(CardinalPonits cardinalPoint) {
		this.cardinalPoint = cardinalPoint;
	}

}
