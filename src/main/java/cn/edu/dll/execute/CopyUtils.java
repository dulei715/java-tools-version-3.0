package cn.edu.dll.execute;

import cn.edu.dll.basic.StringUtil;
import cn.edu.dll.constant_values.ConstantValues;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopyUtils {
    public static boolean fileCopyWithTransferTo(String sourcePath, String destinationPath) {
        try {
            FileInputStream fis = new FileInputStream(sourcePath);
            FileOutputStream fos = new FileOutputStream(destinationPath);
            File outputDirPath = new File(destinationPath).getParentFile();
            if (!outputDirPath.exists()) {
                outputDirPath.mkdirs();
            }
            FileChannel sourceChannel = fis.getChannel();
            FileChannel destinationChannel = fos.getChannel();
            // 使用 transferTo 方法传输数据
            sourceChannel.transferTo(0, sourceChannel.size(), destinationChannel);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("File copying failed.");
            return false;
        }
    }

    public static void main(String[] args) {
        String basicPath = "/Users/admin/MainFiles/test2";
        String sourcePath = StringUtil.join(ConstantValues.FILE_SPLIT, basicPath, "1.txt");
        String destinationPath = StringUtil.join(ConstantValues.FILE_SPLIT, basicPath, "2.txt");
        boolean result = fileCopyWithTransferTo(sourcePath, destinationPath);
        System.out.println(result);
    }
}
