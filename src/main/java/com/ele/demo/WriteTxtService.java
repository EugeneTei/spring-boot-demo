package com.ele.demo;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class WriteTxtService {

    public void appendToRootFile(String fileName, String content) {
        File file = new File(fileName); // 根目錄中的檔案
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) { // true 表示以追加模式寫入
            writer.write(content);
//            writer.newLine(); // 每次新增資料後換行
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
