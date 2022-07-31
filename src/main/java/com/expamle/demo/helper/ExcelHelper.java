package com.expamle.demo.helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;


import com.example.demo.model.DivisionKeys;
import com.example.demo.model.Expenses;

public class ExcelHelper {

	  public static String TYPE = "text/csv";
	  static String[] HEADERs = { "expense_id", "key", "label_id", "expense" };

	  public static boolean hasCSVFormat(MultipartFile file) {
	    if (TYPE.equals(file.getContentType())
	    		|| file.getContentType().equals("application/vnd.ms-excel")) {
	      return true;
	    }
	    return false;
	    }
	    
	  public static List<Expenses> csvToExpenseTable(InputStream is) {
		    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		        CSVParser csvParser = new CSVParser(fileReader,
		            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
		    	
		      List<Expenses> expenseList = new ArrayList<>();

		      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

		      for (CSVRecord csvRecord : csvRecords) {
		    	  Expenses expense = new Expenses(
		              Integer.parseInt(csvRecord.get("expenseid")),
		              csvRecord.get("key"),
		              csvRecord.get("labelid"),
		              Double.parseDouble(csvRecord.get("expense"))
		              );

		    	  expenseList.add(expense);
		      }

		      return expenseList;	
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		    }
		  }

		 
	  }
