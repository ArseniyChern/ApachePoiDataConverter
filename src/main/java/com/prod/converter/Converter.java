package com.prod.converter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.prod.data.DataObject;

@Component
@PropertySource("Converter.properties")
public class Converter {

	// creating the workbook, this is a spreadsheet file
	private XSSFWorkbook workBook = new XSSFWorkbook();

	// creating the sheet, this is a sheet in the spreadsheet
	private XSSFSheet spreadSheet = workBook.createSheet("Data Objects");

	// row for iterating
	private XSSFRow row;

	// rowId for iterating
	private int rowId = 0;

	// file location
	@Value("${file.output.location}")
	private String file_location;

	/**
	 * @param {List<DataObject>}
	 *            data list of dataObjects
	 * @throws IOException
	 */
	public void convert(List<DataObject> data) throws IOException {

		// style to unlock the cell
		CellStyle unlockedCellStyle = workBook.createCellStyle();
		unlockedCellStyle.setLocked(false);
		
		CellStyle lockedCellStyle = workBook.createCellStyle();
		unlockedCellStyle.setLocked(true);
	
	
		row = spreadSheet.createRow(rowId++);

		Cell nameLabel = row.createCell(0);
		nameLabel.setCellValue("Name");
		nameLabel.setCellStyle(lockedCellStyle);

		Cell startTimeLabel = row.createCell(1);
		startTimeLabel.setCellValue("Start Time");
		startTimeLabel.setCellStyle(lockedCellStyle);

		Cell endTimeLabel = row.createCell(2);
		endTimeLabel.setCellValue("End Time");
		endTimeLabel.setCellStyle(lockedCellStyle);

		Cell durationLabel = row.createCell(3);
		durationLabel.setCellValue("Duration");
		durationLabel.setCellStyle(lockedCellStyle);

		// iterate over the list and write it to the corresponding cell in the sheet
		// don't change to stream
		data.forEach((e) -> {

			row = spreadSheet.createRow(rowId++);

			Cell name = row.createCell(0);
			name.setCellValue(e.getName());
			name.setCellStyle(unlockedCellStyle);

			Cell startTime = row.createCell(1);
			startTime.setCellValue(e.getStartTime());
			startTime.setCellStyle(unlockedCellStyle);

			Cell endTime = row.createCell(2);
			endTime.setCellValue(e.getEndTime());
			endTime.setCellStyle(unlockedCellStyle);

			Cell duration = row.createCell(3);
			duration.setCellValue(e.getDuration());
			duration.setCellStyle(unlockedCellStyle);

		});
		
		workBook.lockRevision();

		// write the workbook to file
		write();

	}

	/**
	 * private method to write workBook to file
	 * 
	 * @throws IOException
	 */
	private void write() throws IOException {
		FileOutputStream out = new FileOutputStream(new File(file_location));
		workBook.write(out);
		out.close();

	}

}
