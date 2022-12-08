package com.proyecto.Gpsi.Util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.proyecto.Gpsi.Entity.Marca;
import com.proyecto.Gpsi.Entity.Rol;


public class ExcelUtils {
	
	public static List<Rol> parseExcelFile(InputStream is) {
		try {
    		Workbook workbook = new XSSFWorkbook(is);
     
    		Sheet sheet = workbook.getSheet("roles");
    		Iterator<Row> rows = sheet.iterator();
    		
    		List<Rol> lstCustomers = new ArrayList<Rol>();
    		
    		int rowNumber = 0;
    		while (rows.hasNext()) {
    			Row currentRow = rows.next();
    			
    			// skip header
    			if(rowNumber == 0) {
    				rowNumber++;
    				continue;
    			}
    			
    			Iterator<Cell> cellsInRow = currentRow.iterator();

    			Rol cust = new Rol();
    			
    			int cellIndex = 0;
    			while (cellsInRow.hasNext()) {
    				Cell currentCell = cellsInRow.next();
    				
    				if(cellIndex==0) { // ID
    					cust.setId((int) currentCell.getNumericCellValue());
    				} else if(cellIndex==1) { // Name
    					cust.setName(currentCell.getStringCellValue());
    				}
    				
    				cellIndex++;
    			}
    			
    			lstCustomers.add(cust);
    		}
    		
    		// Close WorkBook
    		workbook.close();
    		
    		return lstCustomers;
        } catch (IOException e) {
        	throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
	}

	public static List<Marca> parseExcelFileMarcas(InputStream is) {
		try {
    		Workbook workbook = new XSSFWorkbook(is);
     
    		Sheet sheet = workbook.getSheet("marcas");
    		Iterator<Row> rows = sheet.iterator();
    		
    		List<Marca> lstMarcas = new ArrayList<Marca>();
    		
    		int rowNumber = 0;
    		while (rows.hasNext()) {
    			Row currentRow = rows.next();
    			
    			// skip header
    			if(rowNumber == 0) {
    				rowNumber++;
    				continue;
    			}
    			
    			Iterator<Cell> cellsInRow = currentRow.iterator();

    			Marca mar = new Marca();
    			
    			int cellIndex = 0;
    			while (cellsInRow.hasNext()) {
    				Cell currentCell = cellsInRow.next();
    				
    				if(cellIndex==0) { // ID
    					mar.setId((int) currentCell.getNumericCellValue());
    				} else if(cellIndex==1) { // Name
    					mar.setName(currentCell.getStringCellValue());
    				}
    				
    				cellIndex++;
    			}
    			
    			lstMarcas.add(mar);
    		}
    		
    		// Close WorkBook
    		workbook.close();
    		
    		return lstMarcas;
        } catch (IOException e) {
        	throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
	}
}