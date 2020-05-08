package com.tsolution.sso.utils;

public class StringUtils {
	public static boolean isNullOrEmpty(String s) {
		return (s == null) || "".equalsIgnoreCase(s.trim());
	}
}
