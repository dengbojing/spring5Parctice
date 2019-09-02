package com.abba;

import com.abba.entity.GPS;
import com.abba.util.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author dengbojing
 */
public class CompanyInfoTest {


    @Test
    public void test() throws IOException {
        Map<Integer, List<String>> map = ExcelReadHelper.read("F:\\data\\01 汽车制造业.csv");
        FileOutputStream fis = new FileOutputStream(new File("F:\\data\\01 汽车制造业.md"),true);
        map.forEach((i, strings) -> {
            if(i != 0){
                String companyName = strings.get(1);
                try {
                    String address = QiChaChaHelper.matchByNameLike(companyName);
                    String locationStr = " ";
                    JSONObject addressObject = JSON.parseObject(address);
                    JSONObject resultObject  = addressObject.getJSONObject("Result");
                    if(resultObject != null){
                        String addressStr = addressObject.getJSONObject("Result").get("Address").toString();
                        if(StringHelper.isNotEmpty(addressStr)){
                            String locationInfo = GaodeMapHelper.address2Location(addressStr,"500100");
                            JSONObject jsonObject = JSON.parseObject(locationInfo);
                            JSONArray jsonArray = jsonObject.getJSONArray("geocodes");
                            if(jsonArray != null && jsonArray.size() > 0){
                                locationStr = jsonArray.getJSONObject(0).get("location").toString();
                            }
                        }
                    }
                    locationStr+="\n";
                    fis.write(locationStr.getBytes());
                } catch (IOException e) {
                    try {
                        fis.flush();
                        fis.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
        });
        fis.flush();
        fis.close();

    }


    @Test
    public void test1() throws IOException {
        Map<Integer, List<String>> map = ExcelReadHelper.read("F:\\data\\01 汽车制造业.csv");
        FileOutputStream fis = new FileOutputStream(new File("F:\\data\\01 汽车制造业.md"),true);
        map.forEach((i, strings) -> {
            if(i != 0){
                String addressStr = strings.get(1);
                try {
                    String locationStr = " ";
                    if(StringHelper.isNotEmpty(addressStr)){
                        String locationInfo = GaodeMapHelper.address2Location(addressStr,"500100");
                        JSONObject jsonObject = JSON.parseObject(locationInfo);
                        JSONArray jsonArray = jsonObject.getJSONArray("geocodes");
                        if(jsonArray != null && jsonArray.size() > 0){
                            locationStr = jsonArray.getJSONObject(0).get("location").toString();
                        }
                    }
                    locationStr+="\n";
                    fis.write(locationStr.getBytes());
                } catch (IOException e) {
                    try {
                        fis.flush();
                        fis.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
        });
        fis.flush();
        fis.close();

    }

    @Test
    public void test2() throws IOException {
        Map<Integer, List<String>> map = ExcelReadHelper.read("F:\\data\\永川归上企业.xlsx");
        FileOutputStream fis = new FileOutputStream(new File("F:\\data\\永川归上企业wgs84.md"),true);
        map.forEach((integer, strings) -> {
            if(integer != 0 && integer != 1){
                String location = strings.get(5);
                location +=","+strings.get(6);
                String wgs84 = " ";
                if(StringHelper.isNotEmpty(location.trim())){
                    String[] locationStr = location.split(",");

                    double lon = Double.parseDouble(locationStr[0]);
                    double lat = Double.parseDouble(locationStr[1]);
                    GPS gps = GPSConverterHelper.bd09_To_Gps84(lat, lon);
                    wgs84 = gps.getLon()+","+gps.getLat();
                }
                wgs84+="\n";
                try {
                    fis.write(wgs84.getBytes());
                } catch (IOException e) {

                }

            }
        });
        fis.flush();
        fis.close();
    }
}
