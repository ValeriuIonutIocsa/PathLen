package com.utils.scripts.gen.path_len;

import java.nio.file.Path;

class FileWithPathLen {

	private final Path filePath;
	private final int pathLength;

	FileWithPathLen(
			final Path filePath,
			final int pathLength) {

		this.filePath = filePath;
		this.pathLength = pathLength;
	}

	void printToConsole() {

		System.out.print("file path: ");
		System.out.print(filePath);
		System.out.print(", length: ");
		System.out.print(pathLength);
		System.out.println();
	}

	int getPathLength() {
		return pathLength;
	}
}
