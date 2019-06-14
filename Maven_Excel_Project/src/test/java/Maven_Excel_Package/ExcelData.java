package Maven_Excel_Package;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelData 
{
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	
	
	public void excelData(String file) throws IOException
	{
		FileInputStream fis= new FileInputStream(file);
		workbook=new XSSFWorkbook(fis);
		System.out.println("asdsa");
	}
	
	public int getRowCount(String sheetname)
	{
		sheet=workbook.getSheet("sheet1");
		int row=sheet.getLastRowNum();
		int rowcount=row+1;
		return rowcount;
	}
	
	public int getColumnCount(String sheetname)
	{
		sheet=workbook.getSheet("sheet1");
		int row1=sheet.getLastRowNum();
		int columncount=sheet.getRow(row1).getLastCellNum();
		return columncount;
	}
	
	public String getData(String sheetname,int row,int column)
	{
		String val=sheet.getRow(row).getCell(column).getStringCellValue();
		return val;
	}
	
	
	
  /*@Test
  public void f() {
  }*/
}
