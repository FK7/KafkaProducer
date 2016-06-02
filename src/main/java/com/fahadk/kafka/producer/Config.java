package com.fahadk.kafka.producer;

/**
 * 
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The Class Config.
 *
 * @author abdul.khan
 */
public class Config {

	/** The input stream. */
	private InputStream inputStream;

	/** The properties. */
	private Properties properties;

	private String name;

	private String path;

	/**
	 * Instantiates a new config.
	 *
	 * @param fileName
	 *            the file name
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	public void load() throws IOException {
		properties = new Properties();
		inputStream = new FileInputStream(path);
		properties.load(inputStream);
	}

	public Config setName(final String name) {
		this.name = name;
		return this;
	}

	public Config setConfigPath(final String path) {
		this.path = path;
		return this;
	}

	/**
	 * Gets the property as value.
	 *
	 * @param key
	 *            the key
	 * @return the property as value
	 */
	public String getProperty(final String key) {
		return properties.getProperty(key);
	}

	/**
	 * Gets the property as long.
	 *
	 * @param key
	 *            the key
	 * @return the property as long
	 */
	public long getPropertyAsLong(final String key) {

		long value = -1;
		try {
			value = Long.parseLong(properties.getProperty(key));
		} catch (NumberFormatException exception) {

		}
		return value;
	}

	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}
}
