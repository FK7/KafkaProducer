package com.fahadk.kafka.producer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaProducer {

	private static final Logger LOGGER = Logger.getLogger(KafkaProducer.class);

	public static void main(String[] args) {

		final Config config = new Config().setName("Kafka producer config")
				.setConfigPath(args[0]/*"src/main/resources/kafka-producer.properties"*/);
		try {
			config.load();
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("error while loading config file : ", e);
		}
		while(true) {
		final Producer<String, String> kafkaProducer = new Producer<String, String>(
				new ProducerConfig(config.getProperties()));
		
		try {
			final FileInputStream fileInputStream = new FileInputStream(args[1]/*"src/main/resources/apache-server.log"*/);
			final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			String log;
			while ((log = bufferedReader.readLine()) != null) {
				final KeyedMessage<String, String> data = new KeyedMessage<String, String>(
						config.getProperties().getProperty("topic"), log);
				kafkaProducer.send(data);
			}
			bufferedReader.close();
			fileInputStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("eror while sending data to kafka server", e);
		}
		
		kafkaProducer.close();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
}