package com.abba.util;

import com.opencsv.CSVReader;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author dengbojing
 */
public class CSVReaderHelper {

    /**
     * 读取csv文件获取数据
     * @param fileName 文件完成路径
     * @return key-行号， value-每行数据
     * @throws IOException
     */
    public static Map<Integer, List<String>> read(String fileName) throws IOException {
        DataInputStream in = new DataInputStream(new FileInputStream(new File(fileName)));
        CSVReader reader = new CSVReader(new InputStreamReader(in, "GBK"));
        List<String[]> list = reader.readAll();
        AtomicInteger i = new AtomicInteger(0);
        return list.stream().collect(Collectors.toMap(value->i.getAndIncrement(),Arrays::asList));
    }
}
