package com.tsolution.sso.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Translator {
	private static MessageSource messageSource;

	@Autowired
	private Translator(MessageSource messageSource) {
		Translator.messageSource = messageSource;
	}

	public static String toLocale(String msgCode, String lang) {
		Locale locale = new Locale(lang);
		return Translator.messageSource.getMessage(msgCode, null, locale);
	}

	public static String toLocale(String msgCode) {
		Locale locale = LocaleContextHolder.getLocale();
		return Translator.messageSource.getMessage(msgCode, null, locale);
	}

	public static String toLocaleByFormatString(String msgCode, Object... msg) {
		Locale locale = LocaleContextHolder.getLocale();
		return String.format(Translator.messageSource.getMessage(msgCode, null, locale), msg);
	}
}
