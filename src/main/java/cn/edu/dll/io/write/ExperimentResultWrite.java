package cn.edu.dll.io.write;


import cn.edu.dll.result.ExperimentResult;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("Duplicates")
public class ExperimentResultWrite extends BasicWrite {
    public ExperimentResultWrite() {
        super(",");
    }

    public ExperimentResultWrite(String OUTPUT_SPLIT_SYMBOL) {
        super(OUTPUT_SPLIT_SYMBOL);
    }

    public void write(List<ExperimentResult> schemeList) {
        if (schemeList.size() < 1) {
            return;
        }
        List<String> attributeList = schemeList.get(0).getAttributeList();
        int i, j;
        List<String> tempValueList;
        try {
            for (i = 0; i < attributeList.size() - 1; i++) {
                super.bufferedWriter.write(attributeList.get(i) + OUTPUT_SPLIT_SYMBOL);
            }
            super.bufferedWriter.write(attributeList.get(i));
            super.bufferedWriter.newLine();
            for (ExperimentResult resultElement : schemeList) {
                tempValueList = resultElement.getValueList();
                for (j = 0; j < tempValueList.size() - 1; j++) {
                    super.bufferedWriter.write(tempValueList.get(j) + OUTPUT_SPLIT_SYMBOL);
                }
                super.bufferedWriter.write(tempValueList.get(j));
                super.bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void write(String outputPath, List<ExperimentResult> schemeList) {
        ExperimentResultWrite experimentWrite = new ExperimentResultWrite();
        experimentWrite.startWriting(outputPath);
        experimentWrite.write(schemeList);
        experimentWrite.endWriting();
    }
}
