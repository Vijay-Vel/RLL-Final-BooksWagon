package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWrite {

	XSSFWorkbook file;
	XSSFSheet s;
	public void writeExcel(String path, String SheetName, int r, int c, String result) throws IOException {
		FileInputStream fin = new FileInputStream(path);
		file = new XSSFWorkbook(fin);
		s=file.getSheet(SheetName);
		s.getRow(r).createCell(c).setCellValue(result);
		FileOutputStream fout = new FileOutputStream(path);
		file.write(fout);
		fout.flush();
		file.close();
	}
	public static void main(String[] args) throws IOException {
		
		  // TODO Auto-generated method stub 
		ExcelWrite e = new ExcelWrite();
		e.writeExcel("C:\\Users\\USER\\Desktop\\TestData3.xlsx", "Sheet2", 1, 2,"PASS");
		 
	}
}
