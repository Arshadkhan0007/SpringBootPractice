package com.example.ExcelSheetHandling.Service;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

@Service
public class ExcelService {

    public void readExcel(InputStream in){
        try(Workbook workbook = WorkbookFactory.create(in)){
            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
            while(sheetIterator.hasNext()){
                Sheet sheet = sheetIterator.next();
                Iterator<Row> rowIterator = sheet.rowIterator();
                while(rowIterator.hasNext()){
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while(cellIterator.hasNext()){

                        Cell cell = cellIterator.next();
                        switch (cell.getCellType()){
                            case STRING:
                                System.out.println("Type: STRING\n" + cell.getStringCellValue());
                                break;

                            case NUMERIC:
                                if(DateUtil.isCellDateFormatted(cell)){
                                    System.out.println("Type: NUMERIC -> DATE\n" + cell.getDateCellValue());
                                } else{
                                    System.out.println("Type: NUMERIC\n" + cell.getNumericCellValue());
                                }
                                break;

                            case BOOLEAN:
                                System.out.println("Type: BOOLEAN\n" + cell.getBooleanCellValue());
                                break;

                            case FORMULA:
                                System.out.println("Type: FORMULA\n" + cell.getCellFormula());
                                break;

                            default:
                                System.out.println("Empty!");
                        }

                    }
                    System.out.println();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
