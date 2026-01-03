package cn.edu.dll.filter.file_filter;


import java.io.File;
import java.io.FileFilter;

public class TxtFilter implements FileFilter {

    @Override
    public boolean accept(File file) {
        String fileName = file.getName();
        return fileName.endsWith(".txt");
    }
}
