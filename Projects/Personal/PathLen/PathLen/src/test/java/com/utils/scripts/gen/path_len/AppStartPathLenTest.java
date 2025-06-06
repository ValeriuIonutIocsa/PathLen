package com.utils.scripts.gen.path_len;

import org.junit.jupiter.api.Test;

import com.utils.test.TestInputUtils;

class AppStartPathLenTest {

	@Test
	void testMain() throws Exception {

		final String[] args;
		final int input = TestInputUtils.parseTestInputNumber("21");
		if (input == 1) {
			args = new String[] {
					"C:\\IVI\\Prog\\JavaGradle\\Scripts\\General\\ReplaceFileInFolders\\" +
							"Projects\\Personal\\ReplaceFileInFolders\\ReplaceFileInFolders\\" +
							"src\\main\\java\\com\\personal\\scripts\\gen\\repl_file_in_folder\\" +
							"AppStartReplaceFileInFolders.java" };
		} else if (input == 2) {
			args = new String[] { "C:\\IVI" };

		} else if (input == 11) {
			args = new String[] { "-dir", "C:\\IVI\\Vitesco\\Prog\\JavaGradle" };
		} else if (input == 12) {
			args = new String[] { "-dir", "D:\\IVI_MISC\\Misc\\mnf\\test" };

		} else if (input == 21) {
			args = new String[] { "-dir", "C:\\IVI\\Vitesco\\Prog\\JavaGradle", ".*\\.java" };

		} else if (input == 101) {
			args = new String[] { "-help" };
		} else if (input == 102) {
			args = new String[] {};
		} else if (input == 103) {
			args = new String[] { "" };

		} else {
			throw new RuntimeException();
		}

		AppStartPathLen.main(args);
	}
}
