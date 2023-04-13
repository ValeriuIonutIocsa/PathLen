package com.utils.scripts.gen.path_len;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class AppStartPathLen {

	private AppStartPathLen() {
	}

	public static void main(
			final String[] args) throws Exception {

		if (args.length < 1) {

			final String helpMessage = createHelpMessage();
			System.err.println("ERROR - insufficient arguments" +
					System.lineSeparator() + helpMessage);
			System.exit(-1);
		}

		if ("-help".equals(args[0])) {

			final String helpMessage = createHelpMessage();
			System.out.println(helpMessage);
			System.exit(0);
		}

		if ("-dir".equals(args[0])) {

			if (args.length < 2) {

				System.err.println("missing second argument");
				System.exit(1);
			}

			final Path folderPath = Paths.get(args[1]).toAbsolutePath().normalize();
			if (!Files.isDirectory(folderPath)) {
				System.err.println("directory does not exist:" +
						System.lineSeparator() + folderPath);

			} else {
				final Pattern filePathFilterPattern;
				if (args.length >= 3) {

					final String filePathFilterPatternString = args[2];
					if (filePathFilterPatternString != null && !filePathFilterPatternString.isBlank()) {
						filePathFilterPattern = Pattern.compile(filePathFilterPatternString);
					} else {
						filePathFilterPattern = null;
					}

				} else {
					filePathFilterPattern = null;
				}

				final List<Path> pathList;
				try (Stream<Path> filePathStream = Files.walk(folderPath)) {

					pathList = filePathStream.filter(path -> filePathFilterPattern == null ||
							filePathFilterPattern.matcher(path.toString()).matches())
							.collect(Collectors.toList());
				}

				final List<FileWithPathLen> fileWithPathLenList = new ArrayList<>();
				for (Path path : pathList) {

					final int pathLength = path.toString().length();
					final FileWithPathLen fileWithPathLen = new FileWithPathLen(path, pathLength);
					fileWithPathLenList.add(fileWithPathLen);
				}

				fileWithPathLenList.sort(Comparator.comparing(
						FileWithPathLen::getPathLength, Comparator.reverseOrder()));

				final int fileWithPathLenListCount = Math.min(10, fileWithPathLenList.size());
				for (int i = 0; i < fileWithPathLenListCount; i++) {

					final FileWithPathLen fileWithPathLen = fileWithPathLenList.get(i);
					fileWithPathLen.printToConsole();
				}
			}

		} else {
			final Path path = Paths.get(args[0]).toAbsolutePath().normalize();

			final int pathLength = path.toString().length();
			final FileWithPathLen fileWithPathLen = new FileWithPathLen(path, pathLength);

			fileWithPathLen.printToConsole();
		}
	}

	private static String createHelpMessage() {

		return "usage: path_len <file-path>" + System.lineSeparator() +
				"path_len -dir <folder-path> (<file_path_filter_pattern>)";
	}
}
