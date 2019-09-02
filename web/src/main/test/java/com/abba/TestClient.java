package com.abba;

import com.abba.model.Company;
import com.abba.util.CSVReaderHelper;
import com.abba.util.StringHelper;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class TestClient {

    @Test
    public void test(){
        int i = 9;
        setVariable(i);
        System.out.println(i);


    }
    public void setVariable(int i){
        i =10;
    }

    @Test
    public void csvTest() throws IOException {
        Map<Integer, List<String>> map = CSVReaderHelper.read("F:\\data\\01 汽车制造业.csv");
        System.out.println(map);
    }

    @Test
    public void setFundsAndFundsType(){
        String value  = "200万元人民币";
        String[] values = value.split("万");
        System.out.println(values[0]+"--"+values[1]);
    }

    @Test
    public void localDateTest(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/M/dd");
        LocalDate date = LocalDate.from(dateTimeFormatter.parse("2005/12/19"));
        System.out.println(date.getYear());
    }

    @Test
    public void testStringHelper(){
        System.out.println(StringHelper.isNullValue("NiLL"));
    }

}
