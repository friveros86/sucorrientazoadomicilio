package com.sucorrientazoadomicilio.services;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class WriteFileService {

	private static final Logger LOGGER = Logger.getLogger(WriteFileService.class.getClass().getName());

	private static final String FOLDER_PATH = "src/main/resources/out/";

	public void writeReportFile(String position, String fileName) {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(FOLDER_PATH + "out"
						+ fileName.substring(fileName.lastIndexOf('.') - 2, fileName.lastIndexOf('.'))+".txt", true),
				StandardCharsets.UTF_8))) {
			writer.write(position.concat("\n"));
		} catch (IOException ex) {
			LOGGER.info(String.format("error in writeReportFile method {}", ex.getMessage()));
		}
	}
}
