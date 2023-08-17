package kr.or.ddit.example1;
import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class workBookrReader {

	public static void main(String[] args) {
		String filePath = "D:\\D_Other";
		String fileName = "workBookWrite.xlsx";

//			for(int i=0; i<workbook.getNumberOfSheets(); i++) {
//				XSSFSheet sheet = workbook.getSheetAt(i);
//				String sheetName = workbook.getSheetName(i);
//			    System.out.println("시트 이름: " + sheetName);
//			}
//			Row row = sheet.getRow(3); 
//			Cell cell = row.getCell(5);
//			System.out.println(cell.getCellType());
//			System.out.println("row  : " + row.getRowNum() + "\t");
//			System.out.println("cell : " + cell.getColumnIndex());
//			String value = cell.getStringCellValue();
//			value = value+"의 변형";
//			System.out.println("value : "+ value);
//			System.out.println("존재하는 row(행)의 수 : "+sheet.getPhysicalNumberOfRows());
//			System.out.println("해당 행의 마지막 cell의 위치: " + row.getLastCellNum()); 
		try {
			FileInputStream file = new FileInputStream(new File(filePath, fileName));

			XSSFWorkbook workbook = new XSSFWorkbook(file);

			XSSFSheet sheet = workbook.getSheetAt(1);

			for (Row row : sheet) {
				for (Cell cell : row) {
					Object obj = (Object) cell.getCellType();

					if (!(cell == null || cell.getCellType() == CellType.BLANK)) {
						switch (cell.getCellType()) {
						case NUMERIC:
							System.out.print((int) cell.getNumericCellValue() + "\t");
							break;
						case STRING:
							System.out.print(cell.getStringCellValue() + "\t");
							break;
						default:
							System.out.println(cell.toString() + "\t");
							break;
						}
					} else {
						System.out.println("null입니다.");
					}

				}
				System.out.println();
				System.out.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
