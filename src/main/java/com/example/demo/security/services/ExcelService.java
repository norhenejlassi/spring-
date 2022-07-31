package com.example.demo.security.services;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.DivisionKeys;
import com.example.demo.model.Expenses;
import com.example.demo.repository.DivisionKeysDataRepository;
import com.example.demo.repository.ExpensesDataRepository;
import com.expamle.demo.helper.ExcelHelper;

@Service
public class ExcelService {

	@Autowired
	  ExpensesDataRepository ExpDatarepository;
	  
	  
	  public void save(MultipartFile file) {
	    try {
	    		
	      List<Expenses> expenseslist =  ExcelHelper.csvToExpenseTable(file.getInputStream());
	      ExpDatarepository.saveAll(expenseslist);
	   
	    } catch (IOException e) {
	      throw new RuntimeException("fail to store excel data: " + e.getMessage());
	    }
	  }

}
