package cn.edu.dll.io.read;

import cn.edu.dll.basic.BasicArrayUtil;
import cn.edu.dll.constant_values.ConstantValues;
import cn.edu.dll.io.print.MyPrint;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.*;

public class XLSXRead {
    private String filePath;

    public XLSXRead(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, List<Object>> readData() {
        Map<String, List<Object>> data = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            CellType cellType;
            String typeName;
            List<Object> tempObjectList;
            for (Row row : sheet) {
                for (Cell cell : row) {
                    typeName = cell.getCellType().toString();
                    cellType = cell.getCellType();
                    Object cellData;
                    switch (cellType) {
                        case STRING:
//                            System.out.print(cell.getStringCellValue() + "\t");
                            cellData = cell.getStringCellValue();
                            data.computeIfAbsent(typeName, k->new ArrayList<>()).add(cellData);
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
//                                System.out.print(cell.getDateCellValue() + "\t");
                                cellData = cell.getDateCellValue();
                            } else {
//                                System.out.print(cell.getNumericCellValue() + "\t");
                                cellData = cell.getNumericCellValue();
                            }
                            data.computeIfAbsent(typeName, k->new ArrayList<>()).add(cellData);
                            break;
                        case BOOLEAN:
//                            System.out.print(cell.getBooleanCellValue() + "\t");
                            cellData = cell.getBooleanCellValue();
                            data.computeIfAbsent(typeName, k->new ArrayList<>()).add(cellData);
                            break;
                        case FORMULA:
                            System.out.print(cell.getCellFormula() + "\t");
                            cellData = cell.getCellFormula();
                            data.computeIfAbsent(typeName, k->new ArrayList<>()).add(cellData);
                            break;
                        default:
                            System.out.print("NULL\t");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public List<List<Object>> readData(Integer sheetIndex, List<Integer> rowIndexList, List<Integer> colIndexList) {
        List<List<Object>> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            List<Object> innerList;
            Cell tempCell;
            CellType cellType;
            for (Integer rowIndex : rowIndexList) {
                Row row = sheet.getRow(rowIndex);
                innerList = new ArrayList<>();
                for (Integer colIndex : colIndexList) {
                    tempCell = row.getCell(colIndex);
                    cellType = tempCell.getCellType();
                    switch (cellType) {
                        case STRING:
//                            System.out.print(cell.getStringCellValue() + "\t");
                            innerList.add(tempCell.getStringCellValue());
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(tempCell)) {
//                                System.out.print(cell.getDateCellValue() + "\t");
                                innerList.add(tempCell.getDateCellValue());
                            } else {
//                                System.out.print(cell.getNumericCellValue() + "\t");
                                innerList.add(tempCell.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
//                            System.out.print(cell.getBooleanCellValue() + "\t");
                            innerList.add(tempCell.getBooleanCellValue());
                            break;
                        case FORMULA:
//                            System.out.print(cell.getCellFormula() + "\t");
                            innerList.add(tempCell.getCellFormula());
                            break;
                        default:
//                            System.out.print("NULL\t");
                            innerList.add(null);
                    }
                }
                data.add(innerList);
            }
        } catch (org.apache.poi.openxml4j.exceptions.OLE2NotOfficeXmlFileException e) {
            System.err.println("这不是标准的 .xlsx 文件");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        String basicPath = "/Volumes/leileidu-mac-extension/2.important_files/MainFiles/1.Research/dataset/8_robot_schedule/data/";
        String filePath = Strings.concat(basicPath, "model_coordinates.xlsx");
        XLSXRead reader = new XLSXRead(filePath);
        List<Integer> rowList = BasicArrayUtil.getIncreaseIntegerNumberList(1, 1, 4);
        List<Integer> colList = BasicArrayUtil.getIncreaseIntegerNumberList(1, 1, 3);
        List<List<Object>> data = reader.readData(0, rowList, colList);
        MyPrint.show2DimensionArray(data, "; ", ConstantValues.LINE_SPLIT);
    }
}
