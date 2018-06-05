package com.prod;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.AbstractApplicationContext;

import com.prod.converter.Converter;
import com.prod.data.DAO;
import com.prod.data.DataObject;
import com.prod.emailSender.ExcelWritesheetSender;

@ComponentScan
public class Main {

	Logger logger = Logger.getLogger(Main.class);

	// Class that will convert data(ex. from list to excel)
	@Autowired
	private Converter converter;

	// Database Access Object
	@Autowired
	private DAO dao;

	@Autowired
	ExcelWritesheetSender sender;

	// Entry point for app
	public static void main(String[] args) throws IOException {

		// Get spring context(where beans are stores)
		ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

		// Start the application
		Main dataConverter = context.getBean(Main.class);

		// Close the context
		((AbstractApplicationContext) context).close();

		Runnable t = () -> {
			while (true) {
				try {
					dataConverter.performConversion();
					dataConverter.performEmailSend();
					Thread.sleep(24 * 60 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};
		
		t.run();

	}
	
	static String getCurrentTime(long time) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		Date date = new Date(time);
		return dateFormat.format(date);
	}

	private void performEmailSend() {
		sender.sendFile("syzzlehd@gmail.com");
	}

	private void performConversion() throws IOException {
		logger.info("Starting Main Application");

		logger.info("Getting data from DB");
		List<DataObject> dataObjects = dao.read();

		logger.info("Converting Data");
		converter.convert(dataObjects);

	}

}
