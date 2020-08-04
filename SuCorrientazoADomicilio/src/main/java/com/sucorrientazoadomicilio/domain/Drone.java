package com.sucorrientazoadomicilio.domain;

import java.util.ArrayList;
import java.util.List;

import com.sucorrientazoadomicilio.services.WriteFileService;

public class Drone {

	private Position position;
	private Delivery delivery;
	private WriteFileService writeFileService;

	public Drone() {
		super();
		this.position = new Position();
		this.delivery = new Delivery(new ArrayList<Address>(), "");
		this.writeFileService = new WriteFileService();
	}

	public Drone(Position position, Delivery delivery) {
		super();
		this.position = position;
		this.delivery = delivery;
	}

	public synchronized void sendDelivery() {

		List<Address> addressList = getDelivery().getAddressList();
	
		for (Address address : addressList) {
			List<Movements> movementsList = address.getMovementsList();
			iterateOverMovements(movementsList);
			System.out.println("final position " + getPosition().getX() + " " + getPosition().getY() + " "
					+ getPosition().getCardinalPoint());
			createDeliveryReport(getDelivery());
		}
	}

	private void createDeliveryReport(Delivery delivery) {
		StringBuilder line = new StringBuilder("(");
		line.append(getPosition().getX());
		line.append(",");
		line.append(getPosition().getY());
		line.append(")");
		line.append(getPosition().getCardinalPoint());
		writeFileService.writeReportFile(line.toString(), delivery.getFilename());
	}

	private synchronized void iterateOverMovements(List<Movements> movementsList) {
		for (Movements movement : movementsList) {
			adjustPosition(movement);
		}
	}

	private  synchronized void adjustPosition(Movements movement) {
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
				getPosition().setCardinalPoint(CardinalPonits.NORTH);
			} else if (getPosition().getCardinalPoint().getValue().equals("S")) {
				getPosition().setCardinalPoint(CardinalPonits.EAST);
			} else if (getPosition().getCardinalPoint().getValue().equals("W")) {
				getPosition().setCardinalPoint(CardinalPonits.SOUTH);
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
