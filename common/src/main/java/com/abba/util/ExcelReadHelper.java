package com.abba.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.apache.poi.ss.usermodel.CellType.*;

/**
 * @author dengbojing
 */
@Slf4j
public class ExcelReadHelper {

    public static Map<Integer,List<String>> read(String path){
        Map<Integer,List<String>> resultMap = new HashMap<>(1024);
        AtomicInteger rowIndex = new AtomicInteger(0);
        try(Workbook wbook = WorkbookFactory.create(new FileInputStream(path))){
            wbook.sheetIterator().forEachRemaining(sheets -> {
                sheets.rowIterator().forEachRemaining(rows -> {
                    List<String> cellList = new ArrayList<>();
                    rows.cellIterator().forEachRemaining(cell -> {
                        String value = " ";
                        switch (cell.getCellType()) {
                            case STRING:
                                value = cell.getRichStringCellValue().getString();
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    value = cell.getDateCellValue().toString();
                                } else {
                                    value = cell.getNumericCellValue()+"";
                                }
                                break;
                            case BOOLEAN:
                                value = cell.getBooleanCellValue()+"";
                                break;
                            case FORMULA:
                                value = cell.getCellFormula();
                                break;
                            case BLANK:
                                break;
                            default:
                                System.out.println();
                        }
                        cellList.add(value);
                    });
                    resultMap.put(rowIndex.getAndIncrement(), cellList);
                });
            });

        } catch (IOException ex){
            log.error(ex.getMessage());
        }
        return resultMap;
    }

    public static void read(Path path){

    }

    public static void main(String[] args) {
        System.out.println(read("F:\\data\\铜梁企业.xlsx"));
    }

}
