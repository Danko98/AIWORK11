package uz.gullbozor.gullbozor.export;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import uz.gullbozor.gullbozor.entity.ArizaEntity;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.apache.poi.ss.util.CellUtil.createCell;

public class ExcelExportUtils {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<ArizaEntity> arizaEntityList;

    public ExcelExportUtils(List<ArizaEntity> arizaEntityList) {
        this.arizaEntityList = arizaEntityList;
        workbook = new XSSFWorkbook();
    }

    private void createCells(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        }else if (value instanceof Double){
            cell.setCellValue((Double) value);
        }else if (value instanceof Boolean){
            cell.setCellValue((Boolean) value);
        }else if (value instanceof Long){
            cell.setCellValue((Long) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }


    private void createHeaderRow(){
        sheet   = workbook.createSheet("Arizalar ro'yxati");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(20);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        createCell(row, 0, "Arizalar ro'yxati", style);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
        font.setFontHeightInPoints((short) 10);

        row = sheet.createRow(1);
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "Id", style);
        createCell(row, 1, "Tel nomer", style);
        createCell(row, 2, "Ism", style);
        createCell(row, 3, "Viloyat", style);
        createCell(row, 4, "Bo'yi", style);
        createCell(row, 5, "Eni", style);
        createCell(row, 6, "Rangi", style);
        createCell(row, 7, "Sana", style);
    }

    private void writeArizaData(){
        int rowCount = 2;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (ArizaEntity arizaEntity : arizaEntityList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row,columnCount++, String.valueOf(arizaEntity.getId()),style);
            createCell(row,columnCount++,arizaEntity.getPhoneNumber(),style);
            createCell(row,columnCount++,arizaEntity.getName(),style);
            createCell(row,columnCount++,arizaEntity.getRegionId(),style);
            createCell(row,columnCount++, String.valueOf(arizaEntity.getHeight()),style);
            createCell(row,columnCount++, String.valueOf(arizaEntity.getWidth()),style);
            createCell(row,columnCount++, String.valueOf(arizaEntity.getColorNumber()),style);
            createCell(row,columnCount++, String.valueOf(arizaEntity.getCreateAt()),style);
        }

    }

    public void exportDataToExcel(HttpServletResponse response) throws IOException {
        createHeaderRow();
        writeArizaData();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

}
