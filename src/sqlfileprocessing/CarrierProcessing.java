package sqlfileprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CarrierProcessing {
	
	public static final String input_file = "C:\\Users\\RegCorp 95\\Downloads\\acefoi.20180410\\carriercodes.txt";
	
	public static Map<String, String> carriermap = new HashMap<String, String>(); 
	
	public CarrierProcessing() {
		
		doParse();
		
	}
	
public void doParse() {
		
		BufferedReader br = null;
		try {
			
			File file = new File(input_file); 
			
			List<Carrier> carrierList = new ArrayList<Carrier>();
			
			br = new BufferedReader(new FileReader(file)); 
			
			Carrier c=null;
			String st;
			String namestring="";
			String codestring="";
			String[] cols;
			String[] data;
			
			
			while ((st = br.readLine()) != null) {
	  			 
			  			c = new Carrier();
			  			
			  			cols = st.split(":");
			  			
			  			data = cols[1].split("—");
			  			
			  			data[0] = data[0].trim();
			  			
			  			if(Character.isDigit(data[0].charAt(0))) {
			  				c.setName(data[0].substring(1).trim());
			  			}
			  			else {
			  				c.setName(data[0].trim());
			  			}
			  			
			  			c.setCode(data[1].trim());
			  			
			  			
			  			
			  		carriermap.put(c.getCode(), c.getName());
			  			
			  		
			  			
			  		//	carrierList.add(c);
	  			
	  			  
	  		  }
			
			
		/*	 String[] columns = {"Code", "Name"};
	  		  
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
		        
		        for(Carrier carrier: carrierList) {
		            Row row = sheet.createRow(rowNum++);
		            
		            row.createCell(0).setCellValue(carrier.getCode());

		            row.createCell(1).setCellValue(carrier.getName());

		            
		        
		        
		        }
		        
		        for(int i = 0; i < columns.length; i++) {
		            sheet.autoSizeColumn(i);
		        }

		        // Write the output to a file
		        FileOutputStream fileOut = new FileOutputStream("C:\\Users\\RegCorp 95\\Downloads\\acefoi.20180410\\carrier_details.xlsx");
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

		CarrierProcessing cp = new CarrierProcessing();
		
		System.out.println(cp.carriermap.get("AFLH"));
		
		
	}

}
