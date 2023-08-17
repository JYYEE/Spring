package kr.or.ddit.example1;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class workBookWrite {
	
	public static void main(String[] args) {
		String filePath = "D:\\D_Other";
		String fileName = "workBookWrite.xlsx";
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("대덕인재개발원1");
		XSSFRow xrow = sheet.createRow(0);
		Cell cell = xrow.createCell(0);
		cell.setCellValue("완성");
		
		sheet.createRow(3).createCell(5).setCellValue("완성2");
		
		XSSFSheet sheet2 = workbook.createSheet("대덕인재개발원2");
		List<Object[]> detail = new ArrayList<>(); 
		detail.add(new Object[]{"ID", "NAME", "PHONE_NUMBER"});
		detail.add(new Object[]{1, "김민욱", "010-1111-1111" });
		detail.add(new Object[]{2, "진현성", "010-2222-2222" });
		detail.add(new Object[]{3, "신국현", "010-3333-3333" });
		detail.add(new Object[]{4, "오대환", "010-4444-4444" });
		
		int rownum = 27;
		for(Object[] obj : detail) {
			Row xrow2 = sheet2.createRow(rownum++);
			int cellnum = 1;
			for(Object obj2 : obj) {
				Cell cell2 = xrow2.createCell(cellnum++);
				if(obj2 instanceof Integer) {
					cell2.setCellValue((Integer)obj2);
				}else if(obj2 instanceof String) {
					cell2.setCellValue((String)obj2);
				}
			}
		}
		
		try {
			FileOutputStream out = new FileOutputStream(new File(filePath, fileName));
			workbook.write(out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
