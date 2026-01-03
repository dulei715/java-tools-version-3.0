package cn.edu.dll.io.read;


import cn.edu.dll.io.write.CSVWrite;

import java.io.*;
import java.util.*;

@SuppressWarnings("Duplicates")
public class CSVRead {
    protected BufferedReader bufferedReader = null;
    public static List<Map<String, String>> readData(String filePath, int lineSize) {
        BufferedReader bufferedReader = null;
        String line = null;
        String[] dataElement;
        int dataSize;
        List<Map<String, String>> elementList = new ArrayList<>();
        List<String> keyList = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
//            line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()).startsWith(CSVWrite.commonTag));   // 循环直到读到不是注释为止
            String[] keyStrs = line.split(",");
            String[] valueStrs = null;
            Map<String, String> tempMap;
            int lineNum = 0;
            while ((line = bufferedReader.readLine()) != null && lineNum < lineSize) {
                if (line.startsWith(CSVWrite.commonTag)) {
                    continue;
                }
                valueStrs = line.split(",");
                tempMap = new HashMap<>();
                int i;
                for (i = 0; i < keyStrs.length && i < valueStrs.length; i++) {
                    tempMap.put(keyStrs[i], valueStrs[i]);
                }
                for (; i < keyStrs.length; i++) {
                    tempMap.put(keyStrs[i], "");
                }
                elementList.add(tempMap);
                ++lineNum;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return elementList;
    }
    public static List<Map<String, String>> readData(String filePath) {
        BufferedReader bufferedReader = null;
        String line = null;
        List<Map<String, String>> elementList = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
//            line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()).startsWith(CSVWrite.commonTag));   // 循环直到读到不是注释为止
            String[] keyStrs = line.split(",");
            String[] valueStrs = null;
            Map<String, String> tempMap;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(CSVWrite.commonTag)) {
                    continue;
                }
                valueStrs = line.split(",");
                tempMap = new HashMap<>();
                int i;
                for (i = 0; i < keyStrs.length && i < valueStrs.length; i++) {
                    tempMap.put(keyStrs[i], valueStrs[i]);
                }
                for (; i < keyStrs.length; i++) {
                    tempMap.put(keyStrs[i], "");
                }
                elementList.add(tempMap);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return elementList;
    }



    public static List<String> readDataLinesWithoutTitle(String filePath) {
        BufferedReader bufferedReader = null;
        String line;
        List<String> elementList = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
//            line = bufferedReader.readLine();
//            while ((line = bufferedReader.readLine()).startsWith(CSVWrite.commonTag));   // 循环直到读到不是注释为止
            while(true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    return elementList;
                } else if (!line.startsWith(CSVWrite.commonTag)) {
                    break;
                }
            }
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(CSVWrite.commonTag)) {
                    continue;
                }
                elementList.add(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return elementList;
    }

    public static String readDataTitle(String filePath) {
        BufferedReader bufferedReader = null;
        String line = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
//            line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()).startsWith(CSVWrite.commonTag));   // 循环直到读到不是注释为止

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return line;
    }
}
