package kr.or.ddit.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {
	
	public static String filePath = "C:\\Users\\PC-03\\Desktop\\jy\\FinalProject\\excel";
	public static String fileName = "발주서form.xlsx";
	
	public static void main(String[] args) throws Exception {
		ExcelWriter excelWriter = new ExcelWriter();
//		excelWriter.readExcel();
		excelWriter.modifyExcel();
//		excelWriter.createExcel();
	}
	
	public void modifyExcel() throws IOException{
		System.out.println("writeOrderForm 실행");
		// 발주서 파일 불러오기
		//String filePath = "C:\\Users\\PC-03\\Desktop\\jy\\FinalProject";
		//String fileName = "새로 저장.xlsx";
		FileInputStream file = new FileInputStream(new File(filePath, fileName));
		BufferedInputStream bis = new BufferedInputStream(file);
		
		XSSFWorkbook workbook = new XSSFWorkbook(bis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		// 메뉴 header 지정
//		sheet.getRow(2).getCell(2).setCellValue("한빛미디어");
//		sheet.getRow(3).getCell(2).setCellValue("02-123-2345");
//		sheet.getRow(4).getCell(2).setCellValue("jyyee4208@naver.com");
//		sheet.getRow(5).getCell(2).setCellValue("서울특별시 서대문구 연희로2길 62 한빛미디어");
		
		// 출판사 정보
		String pub_nm = "한빛미디어";
		String pub_telno = "02-1111-2222";
		String pub_eml_addr = "jyyee4208@naver.com";
		String pub_addr= "서울특별시 서대문구 연희로2길 62 한빛미디어";
		
		// 수주서에 출판사 정보 입력
		sheet.getRow(2).getCell(2).setCellValue(pub_nm);
		sheet.getRow(3).getCell(2).setCellValue(pub_telno);
		sheet.getRow(4).getCell(2).setCellValue(pub_eml_addr);
		sheet.getRow(5).getCell(2).setCellValue(pub_addr);
		
		// 발주 정보
		String brd_accept_ymd = "2023/07/29";	// 발주 요청 수락일자
//		String addr = "대전 중구 오류동 인재개발원";
				
		sheet.getRow(7).getCell(3).setCellValue(brd_accept_ymd);
//		sheet.getRow(8).getCell(3).setCellValue(addr);
		//sheet.getRow(9).getCell(3).setCellFormula("SUM(I14:I24)");	// 전체 합계
		//sheet.getRow(23).getCell(4).setCellFormula("SUM(E14:E23)"); // 수량 합계
		//sheet.getRow(23).getCell(6).setCellFormula("SUM(I14:I24)"); // 전체 합계
		
		// 발주 내역 가져오기
		/*
		
		setList(13, 2, detail);*/
		
		// 다른 이름으로 저장
		FileOutputStream out = new FileOutputStream(new File(filePath, "발주서test.xlsx"));
		workbook.write(out);
		System.out.println("엑셀 출력 완료");
		out.close();
		workbook.close();
		file.close();
	}
	
	// 발주 내역 가져오기
	public void setList(int rownum, int cellnum, List<Object[]> dataList) throws IOException {
//		FileInputStream file = new FileInputStream(new File(filePath, fileName));
//		Workbook workbook = new XSSFWorkbook(file);
//		Sheet sheet = workbook.getSheetAt(0);
//		for (int i = 0; i < dataList.size(); i++) {
//			Object[] array = dataList.get(i); 
//			Row row = sheet.getRow(rownum);
//			if(row == null) {
//				row = sheet.createRow(rownum);
//			}
//			System.out.println("array.length : "+ array.length);
//			for (int j = 0; j < array.length; j++) {
//				Cell cell = row.getCell(cellnum);
//				if(cell == null) {
//					cell = row.createCell(cellnum);
//				}
//				
//				if(array[j] instanceof Double) {
//					cell.setCellValue((Double)array[j]);
//				} else if(array[j] instanceof String) {
//					cell.setCellValue((String)array[j]);
//				}
//				cellnum++;
//			}
//			rownum++;
//		}
//		file.close();
	}
	
	// 금액에 , 처리하기
	public void setComma(File file, int row, int column) throws Exception, IOException {
		// 발주서 파일 가져오기
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		// 셀 스타일 만들기
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		
		// 금액 , 스타일 설정
		XSSFDataFormat format = workbook.createDataFormat();
		cellStyle.setDataFormat(format.getFormat("#,##0"));
		XSSFCell cell = sheet.getRow(row).getCell(column);
		
		// 글자를 숫자로 변환
		double num = Double.parseDouble(cell.toString());
		
		// 셀에 타입, 숫자 설정
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(num);
		cell.setCellStyle(cellStyle);
	}
	
	public void readExcel() {
//		try {
//            //FileInputStream file = new FileInputStream(new File(filePath, "새로 저장.xlsx"));
//
//            // 엑셀 파일로 Workbook instance를 생성한다.
//            XSSFWorkbook workbook = new XSSFWorkbook(file);
//
//            // workbook의 첫번째 sheet를 가저온다.
//            XSSFSheet sheet = workbook.getSheetAt(0);
//
//            // 만약 특정 이름의 시트를 찾는다면 workbook.getSheet("찾는 시트의 이름");
//            // 만약 모든 시트를 순회하고 싶으면
//            // for(Integer sheetNum : workbook.getNumberOfSheets()) {
//            //      XSSFSheet sheet = workbook.getSheetAt(i);
//            // }
//            // 아니면 Iterator<Sheet> s = workbook.iterator() 를 사용해서 조회해도 좋다.
//
//            // 모든 행(row)들을 조회한다.
//            Iterator<Row> rowIterator = sheet.iterator();
//            while (rowIterator.hasNext()) {
//                Row row = rowIterator.next();
//
//                // 각각의 행에 존재하는 모든 열(cell)을 순회한다.
//                Iterator<Cell> cellIterator = row.cellIterator();
//
//                while (cellIterator.hasNext()) {
//                    Cell cell = cellIterator.next();
//
//                    // cell의 타입을 하고, 값을 가져온다.
//                    switch (cell.getCellType()) {
//                        case NUMERIC:
//                            System.out.print((int) cell.getNumericCellValue() + "\t"); //getNumericCellValue 메서드는 기본으로 double형 반환
//                            break;
//                        case STRING:
//                            System.out.print(cell.getStringCellValue() + "\t");
//                            break;
//                    }
//                }
//                System.out.println();
//            }
//            file.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

	}
	
//	public void createPoForm() throws IOException {
//		
//		XSSFWorkbook workbook = new XSSFWorkbook();
//		
//		XSSFSheet sheet = workbook.createSheet("하루북스 발주서");
//		int rownum = 1;
//		int cellnum = 1;
//		//Row row = sheet.createRow(rownum);
//		Cell cell = sheet.createRow(rownum).createCell(cellnum);
//		cell.setCellValue("데이터");
//		
//		// 리스트를 저장
////		for (int i = 0; i < datalist.size(); i++) {
////			Row nRow = sheet.createRow(rownum++);
////			cellnum++;
////			//datalist.get(i).
////		}
//		FileOutputStream outputStream = new FileOutputStream(new File(filePath, "하루북스 발주서.xlsx"));
//		workbook.write(outputStream);
//		outputStream.close();
//		
//	}
}
