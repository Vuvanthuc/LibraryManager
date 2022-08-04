package vn.cpa.api.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class ExcelUtil {

    public static Object convertValue(Cell cell, int type) {
        if(cell == null || CellType.BLANK == cell.getCellType()) {
            return null;
        }
        cell.setCellType(CellType.STRING);
        Object object;
        try {
            switch (type) {
                case 1:
                    object = cell.getStringCellValue().trim().replaceAll("'","");
                    break;
                case 2:
                    object = Integer.valueOf(cell.getStringCellValue().trim().replaceAll("'",""));
                    break;
                case 3:
                    object = Long.valueOf(cell.getStringCellValue().trim().replaceAll("'",""));
                    break;
                case 4:
                    object = DateUtil.formatDatePattern(cell.getStringCellValue().trim().replaceAll("'",""), DateUtil.DDMMYYYY);
                    break;
                default:
                    object = null;
                    break;
            }
            return object;
        } catch (Exception ex) {
            return null;
        }
    }
}
