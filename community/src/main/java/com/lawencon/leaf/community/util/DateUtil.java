package com.lawencon.leaf.community.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	public static LocalDateTime strToDate(final String dateStr) {
		final DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		final LocalDateTime date = LocalDateTime.parse(dateStr, formater);
		return date;
	}

	public static String dateToStr(final LocalDateTime date) {
		final DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		final String dateStr = date.format(formater);
		return dateStr;
	}

	public static LocalDateTime dateNow() {
		return LocalDateTime.now();
	}

	public static LocalTime strToTime(final String dateStr) {
		final DateTimeFormatter formater = DateTimeFormatter.ofPattern("HH:mm:ss");
		final LocalTime time = LocalTime.parse(dateStr, formater);
		return time;
	}

}
