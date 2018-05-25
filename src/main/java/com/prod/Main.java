package com.prod;


import java.io.IOException;
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

@ComponentScan
public class Main {
	
	Logger logger = Logger.getLogger(Main.class);
	
	//Class that will convert data(ex. from list to excel)
	@Autowired
	private Converter converter;

	//Database Access Object
	@Autowired
	private DAO dao;

	//Entry point for app
	public static void main(String[] args) throws IOException {
		
		//Get spring context(where beans are stores)
		ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

		//Start the application
		Main dataConverter = context.getBean(Main.class);
		dataConverter.start();
		
		//Close the context
		((AbstractApplicationContext) context).close();

	}
	
	static String getCurrentTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
    private void start() throws IOException {
        logger.info("Starting Main Application");
        
        logger.info("Getting data from DB");
        List<DataObject> dataObjects = dao.read();
        
        logger.info("Converting Data");
        converter.convert(dataObjects);
        
    }

}
