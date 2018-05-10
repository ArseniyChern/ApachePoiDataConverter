package com.prod.converter;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prod.Main;
import com.prod.data.DataObject;

@RunWith(SpringJUnit4ClassRunner.class)
@PropertySource("Converter.properties")
@ContextConfiguration(classes = { Converter.class })
public class ConverterTest {

	@Autowired
	Converter converter;

	// converter file location
	@Value("${file.output.location}")
	private String file_location;

	@Test
	public void testConverter() throws IOException {

		List<DataObject> mockList = Arrays.asList(new DataObject[] { new DataObject("1", "2", "3", "4"),
				new DataObject("1", "2", "3", "4"), new DataObject("1", "2", "3", "4") });
		converter.convert(mockList);

		assertEquals(readsheet(file_location, 0),mockList);
	}

	public List<DataObject> readsheet(String location,int sheetIndex) throws IOException {
		FileInputStream fis = new FileInputStream(new File(location));
		
		List<DataObject> list = new ArrayList<DataObject>();

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet spreadsheet = workbook.getSheetAt(sheetIndex);
		Iterator<Row> rowIterator = spreadsheet.iterator();
	
		XSSFRow row;
		while (rowIterator.hasNext()) {
			row = (XSSFRow) rowIterator.next();
			
			list.add(new DataObject(
					row.getCell(0).getStringCellValue(),
					row.getCell(1).getStringCellValue(),
					row.getCell(2).getStringCellValue(),
					row.getCell(3).getStringCellValue()
				));
		}
		fis.close();
		return list;
	}

}
