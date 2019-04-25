package com.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataExcel
{
	
  public static Object[][] testReadExcel(String sheetname)
  {
	 File file =new File("C:\\Users\\Training_b6b.00.03\\eclipse-workspace\\SeleniumWebDriverProject\\src\\test\\resources\\Book1.xlsx");
	 Object[][] obj=null;
	 try {
		InputStream is=new FileInputStream(file);
		try {
			
		
			XSSFWorkbook workbook=new XSSFWorkbook(is);
			XSSFSheet sheet1=workbook.getSheet(sheetname);
			obj=new Object[sheet1.getLastRowNum()][];
			for (int i = 1; i <=sheet1.getLastRowNum(); i++) 
			{
				obj[i-1]=new Object[sheet1.getRow(i).getPhysicalNumberOfCells()];
				for (int j = 0; j < sheet1.getRow(i).getPhysicalNumberOfCells(); j++)
				{
					obj[i-1][j]=sheet1.getRow(i).getCell(j).getStringCellValue();
					//System.out.println(sheet1.getRow(i).getCell(j).getStringCellValue());
				}
				//sheet1.getRow(i).createCell(2).setCellValue("valid user");
				//System.out.println("");
			}
			
			is.close();
			//OutputStream os=new FileOutputStream(file);
			//workbook.write(os);
			//os.close();
			workbook.close();
	 }
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return obj;
	 
  }

}