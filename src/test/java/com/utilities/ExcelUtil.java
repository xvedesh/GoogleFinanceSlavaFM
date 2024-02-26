package com.utilities;


import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for reading Excel files. It simplifies reading data from Excel sheets into Java structures.
 */
public class ExcelUtil {

    private Sheet workSheet;
    private Workbook workBook;
    private String path;

    /**
     * Initializes an ExcelUtil instance which loads the workbook and worksheet from the given path and sheet name.
     *
     * @param path The file path of the Excel workbook.
     * @param sheetName The name of the sheet within the workbook to be used.
     */
    public ExcelUtil(String path, String sheetName) {
        this.path = path;
        try {
            FileInputStream ExcelFile = new FileInputStream(path);
            workBook = WorkbookFactory.create(ExcelFile);
            workSheet = workBook.getSheet(sheetName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves all data from the worksheet as a list of maps.
     * Each map represents a row where the key is the column name and the value is the cell content.
     *
     * @return List of maps, each representing a row with column names as keys and cell contents as values.
     */
    public List<Map<String, String>> getDataList() {
        List<String> columns = getColumnsNames();
        List<Map<String, String>> data = new ArrayList<>();

        for (int i = 0; i < rowCount(); i++) {
            Row row = workSheet.getRow(i);
            Map<String, String> rowMap = new HashMap<>();
            for (Cell cell : row) {
                int columnIndex = cell.getColumnIndex();
                rowMap.put(columns.get(columnIndex), cell.toString());
            }
            data.add(rowMap);
        }
        return data;
    }

    /**
     * Retrieves the column names from the first row of the worksheet.
     *
     * @return A list of strings representing the column names.
     */
    public List<String> getColumnsNames() {
        List<String> columns = new ArrayList<>();

        for (Cell cell : workSheet.getRow(0)) {
            columns.add(cell.toString());
        }
        return columns;
    }

    public int rowCount() {
        return workSheet.getLastRowNum() + 1;
    }

}
