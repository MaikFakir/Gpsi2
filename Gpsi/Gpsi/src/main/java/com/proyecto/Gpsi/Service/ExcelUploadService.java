package com.proyecto.Gpsi.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.proyecto.Gpsi.Entity.Marca;
import com.proyecto.Gpsi.Entity.Rol;
import com.proyecto.Gpsi.Entity.TipoProd;
import com.proyecto.Gpsi.Entity.Usuario;
import com.proyecto.Gpsi.Repository.MarcaRepository2;
import com.proyecto.Gpsi.Repository.RolRepository;
import com.proyecto.Gpsi.Repository.RolRepository2;
import com.proyecto.Gpsi.Repository.TipoProdRepository2;
import com.proyecto.Gpsi.Util.ExcelUtils;

@Service
public class ExcelUploadService {

    @Autowired
	RolRepository2 repository2;

	@Autowired
	MarcaRepository2 repository3;

	@Autowired
	 TipoProdRepository2 repository4;
	
	// Store File Data to Database
	public void store(MultipartFile file){
		try {
			List<Rol> lstCustomers = ExcelUtils.parseExcelFile(file.getInputStream());
    		// Save Customers to DataBase
    		repository2.saveAll(lstCustomers);
        } catch (IOException e) {
        	throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
	}

	public void storeMarca(MultipartFile file){
		try {
			List<Marca> lstMarcas = ExcelUtils.parseExcelFileMarcas(file.getInputStream());
    		// Save Customers to DataBase
    		repository3.saveAll(lstMarcas);
        } catch (IOException e) {
        	throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
	}

	public void storeTipoProd(MultipartFile file){
		try {
			List<TipoProd> lstTipoProd = ExcelUtils.parseExcelFileTipoProd(file.getInputStream());
    		// Save Customers to DataBase
    		repository4.saveAll(lstTipoProd);
        } catch (IOException e) {
        	throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
	}



}
