package com.prod.converter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
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
	private XSSFSheet spreadSheet = workBook.createSheet("DataObjects");

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

		XSSFFont defaultFont = workBook.createFont();
		defaultFont.setFontHeightInPoints((short) 10);
		defaultFont.setFontName("Arial");
		defaultFont.setColor(IndexedColors.BLACK.getIndex());
		defaultFont.setBold(false);

		XSSFFont titleCellFont = workBook.createFont();
		titleCellFont.getCTFont().addNewB();

		CellStyle titleCellStyle = workBook.createCellStyle();
		titleCellStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
		titleCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		titleCellStyle.setFont(titleCellFont);
		titleCellStyle.setAlignment(HorizontalAlignment.RIGHT);
		
		CellStyle rightAlignStyle = workBook.createCellStyle();
		rightAlignStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
		rightAlignStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		rightAlignStyle.setFont(titleCellFont);
		rightAlignStyle.setAlignment(HorizontalAlignment.RIGHT);
		CellStyle regularCellStyle = workBook.createCellStyle();
		regularCellStyle.setFont(defaultFont);
		
		row = spreadSheet.createRow(rowId++);

		Cell nameLabel = row.createCell(0);
		nameLabel.setCellValue("Name");
		nameLabel.setCellStyle(titleCellStyle);

		Cell startTimeLabel = row.createCell(1);
		startTimeLabel.setCellValue("Start Time");
		startTimeLabel.setCellStyle(titleCellStyle);

		Cell endTimeLabel = row.createCell(2);
		endTimeLabel.setCellValue("End Time");
		endTimeLabel.setCellStyle(titleCellStyle);

		Cell durationLabel = row.createCell(3);
		durationLabel.setCellValue("Duration");
		durationLabel.setCellStyle(titleCellStyle);

		// iterate over the list and write it to the corresponding cell in the sheet
		// don't change to stream
		data.forEach((e) -> {

			row = spreadSheet.createRow(rowId++);
			

			Cell name = row.createCell(0);
			name.setCellValue(e.getName());
			name.setCellStyle(rightAlignStyle);

			Cell startTime = row.createCell(1);
			startTime.setCellValue(e.getStartTime());
			startTime.setCellStyle(rightAlignStyle);

			Cell endTime = row.createCell(2);
			endTime.setCellValue(e.getEndTime());
			endTime.setCellStyle(rightAlignStyle);
			name.setCellStyle(regularCellStyle);

			Cell duration = row.createCell(3);
			duration.setCellValue(e.getDuration());
			duration.setCellStyle(regularCellStyle);

		});
		
		//set column to wrap around content
		for(int i = 0; i < 4;i++) 
			spreadSheet.autoSizeColumn(i, false);

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
