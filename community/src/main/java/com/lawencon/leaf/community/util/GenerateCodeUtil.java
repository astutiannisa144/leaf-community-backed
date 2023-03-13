package com.lawencon.leaf.community.util;

import java.util.Random;

public class GenerateCodeUtil {
	public static String generateCode(final int totalLength) {
		final int leftLimit = 48;
		final int rightLimit = 122;
		final Random random = new Random();

		final String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(totalLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString.toUpperCase();
	}

	public static String generateNumber(final int totalLength) {
		final int leftLimit = 48;
		final int rightLimit = 57;
		final Random random = new Random();

		final String generatedNumber = random.ints(leftLimit, rightLimit + 1).limit(totalLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedNumber;
	}
}
