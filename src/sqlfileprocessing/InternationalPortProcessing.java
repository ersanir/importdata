package sqlfileprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class InternationalPortProcessing {
	
	public static final String input_file = "C:\\Users\\RegCorp 95\\Downloads\\acefoi.20180410\\appendix_f_0.txt";
	
	public static Map<String, String> internationalport = new HashMap<String, String>();
	
	public InternationalPortProcessing() {
		
		doParse();
		
	}
	
	public void doParse() {
		
		BufferedReader br = null;
		try {
			
			File file = new File(input_file); 
			
			List<Port> internationalportList = new ArrayList<Port>();
			
			br = new BufferedReader(new FileReader(file)); 
			
			Port p=null;
			String st;
			String country = "";
			 
  		  while ((st = br.readLine()) != null) {
  			
  			
		  			
  			  		p = new Port();
		  			st = st.replaceAll("\\s{3,}", "!");
		  			String[] cols = st.split("!");
		  			
		  			System.out.println(st);
		  			
		  			if(cols.length==2) {country = cols[1].trim();}
		  			
		  			
		  			if(cols.length>2) {
		  			p.setCode(cols[1].trim());
		  			p.setName(cols[2].trim()+", " + country);
		  			}
		  			internationalport.put(p.getCode(), p.getName());
		  			
		  	//		internationalportList.add(p);
  	
  			  
  		  }
  		  
/*  	 String[] columns = {"Name", "Code"};
  		  
  		XSSFWorkbook wb = new XSSFWorkbook();
  		
  		Sheet sheet = wb.createSheet("Port Details");
  		
  	   // Create a Font for styling header cells
        Font headerFont = wb.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = wb.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        
        int rowNum = 1;
        
        for(Port port: internationalportList) {
            Row row = sheet.createRow(rowNum++);
            
            row.createCell(0).setCellValue(port.getName());

            row.createCell(1).setCellValue(port.getCode());

          //  row.createCell(2).setCellValue(port.getType());
        
        
        }
        
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("C:\\Users\\RegCorp 95\\Downloads\\acefoi.20180410\\international_port_details.xlsx");
        wb.write(fileOut);
        fileOut.close();

        // Closing the workbook
        wb.close();
        
        */
  		  
			
			
		}catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } finally {
              	 
        	if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
               
                        	
        }
        
        
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		InternationalPortProcessing ip = new InternationalPortProcessing();

	}

}
