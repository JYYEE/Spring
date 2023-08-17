package kr.or.ddit.example1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class workBookEtc {
	public static String filePath = "D:\\D_other";
	public static String fileNm = "workBookEtc.xlsx";

	public static void main(String[] args) {
		// 1. XSSFWorkbook 클래스를 사용하여 workbook 객체 생성
		XSSFWorkbook workbook = new XSSFWorkbook();

		// 2. Sheet 클래스를 사용하여 sheet 객체 생성
		Sheet sheet = workbook.createSheet("대덕인재개발원");

		// 3. CellStyle 클래스를 사용하여 셀 스타일 생성
		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex()); // 배경색 지정
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderLeft(BorderStyle.MEDIUM); // 경계선 스타일 지정
		style.setBorderRight(BorderStyle.MEDIUM);
		style.setBorderTop(BorderStyle.MEDIUM);
		style.setBorderBottom(BorderStyle.MEDIUM);

		// 4. 셀에 값 쓰기
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue(10);

		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue(20);

		// 5. 셀에 함수 쓰기
		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellFormula("SUM(A1:A2)");

		// 6. 셀 병합
		sheet.addMergedRegion(new CellRangeAddress(2, 3, 0, 1));

		Random random = new Random();

		// 7. 행과 셀 반복문으로 생성하면서 스타일 적용
			row = sheet.createRow(4);
			for (int j = 0; j < 3; j++) {
				cell = row.createCell(j);
				cell.setCellValue(random.nextInt(10) + 1);
				cell.setCellStyle(style);
		}
		
		row = sheet.createRow(5);
		cell = row.createCell(0);
		cell.setCellFormula("SUM(A5:C5)");
		cell.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(5, 6, 0, 2));

		// 8. 파일에 쓰기
		try {
			FileOutputStream out = new FileOutputStream(new File(filePath, fileNm));
			workbook.write(out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}