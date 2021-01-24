package com.example.springback.util.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class MessageProperties {

	private static final String FILE = "message.properties";
	private static MessageProperties messageProperties = null;
	Properties prop = null;

	private MessageProperties() {
		prop = new Properties();
	}

	public static synchronized MessageProperties getInstance() {
		if (messageProperties == null) {
			messageProperties = new MessageProperties();
		}
		return messageProperties;
	}

	public String getValue(String key) {

		InputStream is = getClass().getClassLoader().getResourceAsStream(FILE);
		try {
			prop.load(is);
		} catch (IOException ioEx) {
			log.error("Cause: " + ioEx.getCause());
			log.error("Message: " + ioEx.getMessage());
		}
		return prop.getProperty(key);
	}

	@Deprecated
	public String getValue(String key, String traceCode) {

		InputStream is = getClass().getClassLoader().getResourceAsStream(FILE);
		try {
			prop.load(is);
		} catch (IOException ioEx) {
			log.error("trace: " + traceCode + " Cause: " + ioEx.getCause());
			log.error("trace: " + traceCode + " Message: " + ioEx.getMessage());
		}
		return prop.getProperty(key);
	}

}
