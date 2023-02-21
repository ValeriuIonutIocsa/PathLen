package com.utils.scripts.gen.path_len;

import java.nio.file.Path;
import java.nio.file.Paths;

final class AppStartPathLen {

	private AppStartPathLen() {
	}

	public static void main(
			final String[] args) {

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

		Path filePath = Paths.get(args[0]);
		filePath = filePath.toAbsolutePath().normalize();
		System.out.println("file path:" + System.lineSeparator() + filePath);

		final int pathLength = filePath.toString().length();
		System.out.println("path length: " + pathLength);
	}

	private static String createHelpMessage() {

		return "usage: java replace_file_in_folders <file-path>";
	}
}
