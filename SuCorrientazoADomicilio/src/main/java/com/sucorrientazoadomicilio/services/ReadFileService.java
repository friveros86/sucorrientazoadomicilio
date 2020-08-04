package com.sucorrientazoadomicilio.services;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFileService {

	private static final String FOLDER_PATH = "src/main/resources/in";

	public List<File> readFiles() {
		final File folder = new File(FOLDER_PATH);
		final List<File> fileList = Arrays.asList(folder.listFiles());
		return fileList.parallelStream().filter(file -> file.isFile()).collect(Collectors.toList());
	}

	public String readLines(File file) throws URISyntaxException, IOException {
		Path path = Paths.get(file.getPath());

		Stream<String> lines = Files.lines(path);
		String data = lines.collect(Collectors.joining("\n"));
		System.out.println(data);
		lines.close();
		return data;
	}
}
