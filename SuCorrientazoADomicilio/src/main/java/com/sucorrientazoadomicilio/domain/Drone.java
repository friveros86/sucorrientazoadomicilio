package com.sucorrientazoadomicilio.domain;

import java.util.List;

public class Drone {

	private Position position;
	private Delivery delivery;

	public void sendDelivery() {

		List<Address> addressList = getDelivery().getAddressList();
		iterateOverAddresses(addressList);
	}

	private void iterateOverAddresses(List<Address> addressList) {
		for (Address address : addressList) {
			List<Movements> movementsList = address.getMovementsList();
			iterateOverMovements(movementsList);
		}
	}

	private void iterateOverMovements(List<Movements> movementsList) {
		for (Movements movement : movementsList) {
			adjustPosition(movement);
		}
	}

	private void adjustPosition(Movements movement) {
		switch (movement.getDescription()) {
		case "A":
			if (getPosition().getCardinalPoint().getValue().equals("N")) {
				getPosition().setY(getPosition().getY() + 1);
			} else if (getPosition().getCardinalPoint().getValue().equals("E")) {
				getPosition().setX(getPosition().getX() + 1);
			} else if (getPosition().getCardinalPoint().getValue().equals("S")) {
				getPosition().setY(getPosition().getY() - 1);
			} else if (getPosition().getCardinalPoint().getValue().equals("W")) {
				getPosition().setX(getPosition().getX() - 1);
			}

			break;

		case "D":

			if (getPosition().getCardinalPoint().getValue().equals("N")) {
				getPosition().setCardinalPoint(CardinalPonits.EAST);
			} else if (getPosition().getCardinalPoint().getValue().equals("E")) {
				getPosition().setCardinalPoint(CardinalPonits.SOUTH);
			} else if (getPosition().getCardinalPoint().getValue().equals("S")) {
				getPosition().setCardinalPoint(CardinalPonits.WEST);
			} else if (getPosition().getCardinalPoint().getValue().equals("W")) {
				getPosition().setCardinalPoint(CardinalPonits.NORTH);
			}

			break;

		case "I":

			if (getPosition().getCardinalPoint().getValue().equals("N")) {
				getPosition().setCardinalPoint(CardinalPonits.WEST);
			} else if (getPosition().getCardinalPoint().getValue().equals("E")) {
				getPosition().setCardinalPoint(CardinalPonits.SOUTH);
			} else if (getPosition().getCardinalPoint().getValue().equals("S")) {
				getPosition().setCardinalPoint(CardinalPonits.EAST);
			} else if (getPosition().getCardinalPoint().getValue().equals("W")) {
				getPosition().setCardinalPoint(CardinalPonits.NORTH);
			}

			break;
		}
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

}
