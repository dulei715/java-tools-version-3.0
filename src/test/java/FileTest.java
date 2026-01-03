import org.apache.logging.log4j.util.Strings;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class FileTest {
    @Test
    public void fun1() {
        String basicPath = "/Volumes/leileidu-mac-extension/2.important_files/MainFiles/1.Research/dataset/8_robot_schedule/data/";
        String filePath = Strings.concat(basicPath, "model_coordinates.xlsx");
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // 读取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);

            // 遍历行
            for (Row row : sheet) {
                // 遍历单元格
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                System.out.print(cell.getDateCellValue() + "\t");
                            } else {
                                System.out.print(cell.getNumericCellValue() + "\t");
                            }
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        case FORMULA:
                            System.out.print(cell.getCellFormula() + "\t");
                            break;
                        default:
                            System.out.print("NULL\t");
                    }
                }
                System.out.println(); // 换行
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
