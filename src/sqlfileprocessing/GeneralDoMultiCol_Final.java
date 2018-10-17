package sqlfileprocessing;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
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

public class GeneralDoMultiCol_Final {

	//NOTE: the email column must be the last column !!!!!
	public static final String input_file = "C:\\Users\\RegCorp 95\\Downloads\\acefoi.20180410\\ACEFOI.20180410.txt";

	PortProcessing pp = new PortProcessing();
	CarrierProcessing cp = new CarrierProcessing();
	InternationalPortProcessing ip = new InternationalPortProcessing();


	

	public GeneralDoMultiCol_Final() {

	}

	public void Do() {
		BufferedReader br = null;
		try {

			boolean description=false;
			boolean marksamp=false;

			String shipperaddress;
			String qty="";
			String quantity="";
			String qtyunit="";
			int q = 0;
			String weight="";
			String measure="";
			String measurestring="";
			int measurement=0;
			String measureunit="";
			String foreignport="";
			String consignee="";
			String notifyAddress="";
			String placeOfReceipt="";
			String marks="";
			String st2="";
			int w=0;


			String[] cols = null;

			String[] shipper = null;


			File file = new File(input_file); 


			List<Record> recordList = new ArrayList<Record>();

			br = new BufferedReader(new FileReader(file)); 

			Record r = null;

			String st; 
			while ((st = br.readLine()) != null) {



				st = st.replaceAll("\\s{4,}", "splithere");
				cols = st.split("splithere");
				/* ArrayList<String> colswithoutspace = new ArrayList<String>();

    			 for(int i =0 ;i< cols.length;i++) {
    				 if(cols[i].trim().length() > 0) {

    					 colswithoutspace.add(cols[i]);
    				 }
    			 }*/

				if(cols[0].startsWith("00")) {

					if(r!=null) { recordList.add(r); }

					r = new Record();
					marksamp=false;
					description=false;


					r.setMasterBl(cols[0].substring(2));
					if(cols[1].trim().length()> 5) {
						r.setLandingBill(cols[1]);
						r.setCarrierCode(cols[1].substring(0, 4));
						if(CarrierProcessing.carriermap.get(r.getCarrierCode())!=null) {
							r.setCarrierName(CarrierProcessing.carriermap.get(r.getCarrierCode()));
						}else {
							r.setCarrierName("");
						}
						r.setVoyageNumber(cols[2]);

					}else {
						r.setVoyageNumber(cols[1]);
						r.setLandingBill("");
						r.setCarrierCode("");
						r.setCarrierName("");
					}

				}


				if(cols[0].startsWith("10")) { 

					r.setVesselName(cols[0].substring(8));
					r.setArrivalDate(cols[cols.length-1]);
					// String weight = cols[3].replace(" ", "-");
					if(PortProcessing.usport.get(cols[1].substring(0, 4))!=null) {
						r.setUsPort(PortProcessing.usport.get(cols[1].substring(0, 4)));
					}else {
						r.setUsPort("");
					}
					st2 = cols[2].replaceAll("\\s{1,}", "-");
					String[] weightcols = st2.split("-");
					if(weightcols.length>1)
					{
						qty = weightcols[0];

						quantity = qty.replaceAll("[^\\d.]", "");
						q = Integer.parseInt(quantity);
						r.setQuantity(q);

						qtyunit = qty.replaceAll("[\\d.]", "");

						r.setQuantityUnit(qtyunit);


						weight = weightcols[1];
						weight = weight.replaceAll("[^\\d.]", "");
						w=0;
						if(weight!=null&& !weight.equals(""))
						{ w = Integer.parseInt(weight);}
						r.setWeight(w);

						measure = weightcols[2];


						measurestring = measure.replaceAll("[^\\d.]", "");
						measurement = 0;
						if(measurestring!=null&&! "".equals(measurestring)) {
							measurement = Integer.parseInt(measurestring);}

						r.setMeasurement(measurement);


						measureunit = measure.replaceAll("[\\d.]", "");

						r.setMeasureUnit(measureunit);
						placeOfReceipt="";

						for(int i=3;i<weightcols.length;i++) {

							placeOfReceipt = placeOfReceipt + weightcols[i]+" ";


						}

						r.setPlaceOfReceipt(placeOfReceipt.substring(1));


					}


					if(cols.length==7) {

						if(PortProcessing.usport.get(cols[3])!=null) {

							r.setDistributionPort(PortProcessing.usport.get(cols[3]));

						}
						
						else if(InternationalPortProcessing.internationalport.get(cols[3])!=null) {
							
							r.setDistributionPort(InternationalPortProcessing.internationalport.get(cols[3]));
						}

					}


					if(cols[1].indexOf('K')!=-1) {
						foreignport = cols[1].substring(cols[1].indexOf('K')+1);}

					//	 System.out.println(foreignport);


					if(InternationalPortProcessing.internationalport.get(foreignport)!=null) {

						r.setForeignPort(InternationalPortProcessing.internationalport.get(foreignport));
					}



				}

				if(cols[0].startsWith("20")) { 

					if(cols[1].length()<16)
					{r.setSeal(cols[1]);



					}
					else {


						r.setSeal("");



					}

					for(String x : cols) {
						if(x.length()>28) {
							r.setContainerType(x.substring(23, 27));
						}
					}




				}


				if(cols[0].startsWith("30")) { 


					shipperaddress = "";
					for(int i=0;i<cols.length;i++)
					{
						shipperaddress+= cols[i]+" ";

					}

					r.setShipperAddress(shipperaddress.substring(2));

					shipper = shipperaddress.split("\\s{1,}");

					r.setOriginCountry(shipper[shipper.length-1]);

				}


				if(cols[0].startsWith("40")) { 

					consignee="";
					for(int i=0;i<cols.length;i++)
					{
						consignee+= cols[i]+" ";

					}

					r.setConsignee(consignee.substring(2));


				}


				if(cols[0].startsWith("50")) { 

					r.setNotifyParty(cols[0].substring(2));

					notifyAddress = "";

					for(int i=1;i<cols.length;i++)
					{
						notifyAddress+= cols[i]+" ";

					}

					r.setNotifyAddress(notifyAddress);



				}


				if(cols[0].startsWith("60")) { 

					if(description==false) {

						r.setContainerNumber(cols[0].substring(2));

						r.setProductDescription(cols[1].replaceAll("\\d", ""));
						description= true;
					}


				}


				if(cols[0].startsWith("80")) { 
					if(marksamp==false) {
						marks = "";

						for(int i=1;i<cols.length;i++) {
							marks = marks+ cols[i];
						}

						r.setMarks(marks);
						marksamp=true;

					}


				}




			}

			String[] columns = {"Bill of Lading", "Voyage Number", "Vessel Name", "Arrival Date", "Weight", "Weight(LB)", "Shipper Details",  "Consignee", "Notify Party", "Notify Address", "Container Number", "Product Description", "Quantity", "Quantity Unit","Measurement" , "Measurement Unit","Place of Receipt", "Marks & Numbers", "Master B/L", "Seal", "Carrier Code", "Carrier Name", "US Port" , "Container Type", "Distribution Port", "Foreign Port"};

			XSSFWorkbook wb = new XSSFWorkbook();

			// CreationHelper createHelper = wb.getCreationHelper();

			// Create a Sheet
			Sheet sheet = wb.createSheet("Import Records");

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
			for(Record rec: recordList) {
				Row row = sheet.createRow(rowNum++);

				row.createCell(0).setCellValue(rec.getLandingBill());

				row.createCell(1).setCellValue(rec.getVoyageNumber());

				row.createCell(2).setCellValue(rec.getVesselName());

				row.createCell(3).setCellValue(rec.getArrivalDate());

				row.createCell(4).setCellValue(rec.getWeight());

				row.createCell(5).setCellValue(rec.getWeightlb());

				row.createCell(6).setCellValue(rec.getShipperAddress());

				//    row.createCell(7).setCellValue(rec.getOriginCountry());

				row.createCell(7).setCellValue(rec.getConsignee());

				row.createCell(8).setCellValue(rec.getNotifyParty());

				row.createCell(9).setCellValue(rec.getNotifyAddress());

				row.createCell(10).setCellValue(rec.getContainerNumber());

				row.createCell(11).setCellValue(rec.getProductDescription());

				row.createCell(12).setCellValue(rec.getQuantity());

				row.createCell(13).setCellValue(rec.getQuantityUnit());

				row.createCell(14).setCellValue(rec.getMeasurement());

				row.createCell(15).setCellValue(rec.getMeasureUnit());

				row.createCell(16).setCellValue(rec.getPlaceOfReceipt());

				row.createCell(17).setCellValue(rec.getMarks());

				row.createCell(18).setCellValue(rec.getMasterBl());

				row.createCell(19).setCellValue(rec.getSeal());

				row.createCell(20).setCellValue(rec.getCarrierCode());

				row.createCell(21).setCellValue(rec.getCarrierName());

				row.createCell(22).setCellValue(rec.getUsPort());

				row.createCell(23).setCellValue(rec.getContainerType());

				row.createCell(24).setCellValue(rec.getDistributionPort());

				row.createCell(25).setCellValue(rec.getForeignPort());
			}

			// Resize all columns to fit the content size
			for(int i = 0; i < columns.length; i++) {
				sheet.autoSizeColumn(i);
			}

			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\RegCorp 95\\Downloads\\acefoi.20180410\\poi-generated-file16.xlsx");
			wb.write(fileOut);
			fileOut.close();

			// Closing the workbook
			wb.close();




			/*	  for(Record r2 : recordList) {

    			  System.out.println(r2.getLandingBill()+"-"+ r2.getVoyageNumber()+"-"+ r2.getVesselName()+"-"+r2.getArrivalDate()+"-"+r2.getWeight()+"-"+ r2.getShipperName());

    		  }  */




		} catch (IOException ioe) {
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
		GeneralDoMultiCol_Final generalDo = new GeneralDoMultiCol_Final();
		generalDo.Do();
	}
}
