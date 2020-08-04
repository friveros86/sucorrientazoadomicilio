package com.sucorrientazoadomicilio;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import com.sucorrientazoadomicilio.domain.Address;
import com.sucorrientazoadomicilio.domain.Delivery;
import com.sucorrientazoadomicilio.domain.Drone;
import com.sucorrientazoadomicilio.domain.Movements;
import com.sucorrientazoadomicilio.services.ReadFileService;

public class SuCoorientazoADomiciolio {

	private static List<Drone> dronesList = new ArrayList<>();
	private static int droneLimit = 20;
	private static AtomicInteger droneAmount = new AtomicInteger(0);

	private static final Logger LOGGER = Logger.getLogger(SuCoorientazoADomiciolio.class.getClass().getName());

	public static void main(String[] args) {
		LOGGER.info("delivery proccess has started");
		ReadFileService readFileService = new ReadFileService();
		List<File> fileList = readFileService.readFiles();
		fileList.parallelStream().forEach(file -> sendLunches(readFileService, file));
		dronesList.parallelStream().forEach(drone -> drone.sendDelivery());
	}

	private static synchronized void addNewDrone(Drone drone) {

		if (droneAmount.get() < droneLimit) {
			dronesList.add(drone);
			droneAmount.set(droneAmount.get()+1);
			
		}else {
			LOGGER.info("there aren't avaliables drones");	
		}
	}


	private static synchronized void sendLunches(ReadFileService readFileService, File file) {
		try {
			String addresInStringFotmat = readFileService.readLines(file);
			List<String> addresList = Arrays.asList(addresInStringFotmat.split(System.getProperty("line.separator")));
			List<Address> addressObjectList = iterateOverAddressCharacterByCharracter(addresList);
			Delivery delivery = new Delivery(addressObjectList, file.getName());
			Drone drone = new Drone();
			drone.setDelivery(delivery);
			addNewDrone(drone);
			
		} catch (URISyntaxException | IOException e) {
			LOGGER.info(String.format("error in sendlunch method {}", e.getMessage()));
			;
		}
	}

	private static List<Address> iterateOverAddressCharacterByCharracter(List<String> addressList) {
		List<Address> addressListResult = new ArrayList<>();
		for (String address : addressList) {
			char[] letters = address.toCharArray();
			List<Movements> movementList = new ArrayList<>();
			Address addressObject = buildAddressObject(letters, movementList);
			LOGGER.info(String.format("finish delivery for one direction"));
			addressListResult.add(addressObject);
		}
		return addressListResult;
	}

	private static Address buildAddressObject(char[] letters, List<Movements> movementList) {
		Address address = new Address();
		for (char letter : letters) {
			addMomentsToList(movementList, letter);
		}
		address.setMovementsList(movementList);
		return address;
	}

	private static void addMomentsToList(List<Movements> movementList, char letter) {
		switch (letter) {
		case 'A':
			movementList.add(Movements.A);
			break;
		case 'D':
			movementList.add(Movements.D);
			break;
		case 'I':
			movementList.add(Movements.I);
			break;
		}
	}
}
