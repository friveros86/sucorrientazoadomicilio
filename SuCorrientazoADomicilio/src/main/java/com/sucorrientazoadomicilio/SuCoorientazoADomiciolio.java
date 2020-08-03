package com.sucorrientazoadomicilio;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.sucorrientazoadomicilio.domain.Address;
import com.sucorrientazoadomicilio.domain.Delivery;
import com.sucorrientazoadomicilio.domain.Drone;
import com.sucorrientazoadomicilio.domain.Movements;

public class SuCoorientazoADomiciolio {

	public static void main(String[] args) {

		ReadFileService readFileService = new ReadFileService();
		Drone drone = new Drone();
		List<File> fileList = readFileService.readFiles();
		for (File file : fileList) {
			sendLunchs(readFileService, drone, file);
		}
	}

	private static void sendLunchs(ReadFileService readFileService, Drone dron, File file) {
		try {
			String addresInStringFotmat = readFileService.readLines(file);
			List<String> addresList = Arrays.asList(addresInStringFotmat.split(System.getProperty("line.separator")));
			List<Address> addressObjectList = iterateOverAddressCharacterByCharracter(addresList);
			Delivery delivery = new Delivery(addressObjectList);
			dron.setDelivery(delivery);
			dron.sendDelivery();
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
	}

	private static List<Address> iterateOverAddressCharacterByCharracter(List<String> addressList) {
		List<Address> addressListResult = new ArrayList<>();
		for (String address : addressList) {
			char[] letters = address.toCharArray();
			List<Movements> movementList = new ArrayList<>();
			Address addressObject = buildAddressObject(letters, movementList);
			System.out.println("finish one direction");
			addressListResult.add(addressObject);
		}
		return addressListResult;
	}

	private static Address buildAddressObject(char[] letters, List<Movements> movementList) {
		Address address = new Address();
		for (char letter : letters) {
			addMomentsToList(movementList, letter);
			System.out.println(letter);
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
			movementList.add(Movements.D);
			break;
		}
	}
}
