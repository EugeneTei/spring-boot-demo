package com.ele.demo;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CalculateRecordData {

    public static void main(String[] args) {

        Integer record = 10000;
        Integer loopCount = 8;

        String fileName = record + "record.txt";
        String content = getContent(fileName);

        BigDecimal averageTotalTime = getAverageTotalTime(content, loopCount);
        BigDecimal perRecordTime = getPerRecordTime(averageTotalTime, record);

        System.out.println("AverageTotalTime: " + averageTotalTime); // 6.3
        System.out.println("PerRecordTime: " + perRecordTime); // 0.06
    }

    public static String getContent(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            if (content.endsWith("+")) {
                content = content.substring(0, content.length() - 1);
            }

            return content;
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing numbers: " + e.getMessage());
        }
        return null;
    }

    private static BigDecimal getAverageTotalTime(String content, int loopCount) {
        BigDecimal sum = new BigDecimal(getSum(content));
        BigDecimal loopCountBig = new BigDecimal(loopCount);
        return sum.divide(loopCountBig);
    }

    private static int getSum(String content) {
        String[] numbers = content.split("\\+");
        int sum = 0;
        for (String number : numbers) {
            sum += Integer.parseInt(number);
        }
        return sum;
    }

    private static BigDecimal getPerRecordTime(BigDecimal averageTotalTime, Integer record) {
        return averageTotalTime.divide(new BigDecimal(record));
    }
}
