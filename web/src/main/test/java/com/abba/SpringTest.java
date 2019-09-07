package com.abba;


import com.abba.config.HibernateConfig;
import com.abba.config.RootConfig;
import com.abba.config.ServletConfig;
import com.abba.dao.ICompanyDao;
import com.abba.model.po.Company;
import com.abba.util.CSVReaderHelper;
import com.abba.util.StringHelper;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/*@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = {  RootConfig.class, ServletConfig.class, HibernateConfig.class },
        loader = AnnotationConfigContextLoader.class )*/
public class SpringTest {

    @Autowired
    private ICompanyDao<Company> companyDao;

    private Map<String,String> regionMap = new HashMap<>(64);
    {
        regionMap.put("万州区","渝东北片区");
        regionMap.put("涪陵区","主城及渝西片区");
        regionMap.put("渝中区","主城及渝西片区");
        regionMap.put("大渡口区","主城及渝西片区");
        regionMap.put("江北区","主城及渝西片区");
        regionMap.put("沙坪坝区","主城及渝西片区");
        regionMap.put("九龙坡区","主城及渝西片区");
        regionMap.put("南岸区","主城及渝西片区");
        regionMap.put("北碚区","主城及渝西片区");
        regionMap.put("綦江区","主城及渝西片区");
        regionMap.put("大足区","主城及渝西片区");
        regionMap.put("渝北区","主城及渝西片区");
        regionMap.put("巴南区","主城及渝西片区");
        regionMap.put("黔江区","渝东南片区");
        regionMap.put("长寿区","主城及渝西片区");
        regionMap.put("江津区","主城及渝西片区");
        regionMap.put("合川区","主城及渝西片区");
        regionMap.put("永川区","主城及渝西片区");
        regionMap.put("南川区","主城及渝西片区");
        regionMap.put("璧山区","主城及渝西片区");
        regionMap.put("铜梁区","主城及渝西片区");
        regionMap.put("潼南区","主城及渝西片区");
        regionMap.put("荣昌区","主城及渝西片区");
        regionMap.put("开州区","渝东北片区");
        regionMap.put("梁平区","渝东北片区");
        regionMap.put("武隆区","渝东南片区");
        regionMap.put("城口县","渝东北片区");
        regionMap.put("丰都县","渝东北片区");
        regionMap.put("垫江县","渝东北片区");
        regionMap.put("忠县","渝东北片区");
        regionMap.put("云阳县","渝东北片区");
        regionMap.put("奉节县","渝东北片区");
        regionMap.put("巫山县","渝东北片区");
        regionMap.put("巫溪县","渝东北片区");
        regionMap.put("石柱土家族自治县","渝东南片区");
        regionMap.put("秀山土家族苗族自治县","渝东南片区");
        regionMap.put("酉阳土家族苗族自治县","渝东南片区");
        regionMap.put("彭水苗族土家族自治县","渝东南片区");

    }

    @Test
    public void companyAdd() throws IOException {
        File file = new File("F:\\data\\20190817\\");
        Stream<File> stream = Stream.of(file.listFiles());
        stream.forEach(file1 -> {
            Map<Integer, List<String>> map = null;
            try {
                map = CSVReaderHelper.read(file1.getPath());
                map.forEach((i, strings) -> {
                    if(i != 0){
                        Company company = new Company();
                        company.setName(strings.get(0));
                        company.setAddress(strings.get(1));
                        setFundsAndFundsType(file1.getName(),strings.get(2),company);
                        company.setListed(strings.get(3));
                        company.setHighTech(strings.get(4));
                        company.setPatent(strings.get(5));
                        company.setFinancing(strings.get(6));
                        company.setIndividual(strings.get(7));
                        company.setStatus(strings.get(8));
                        company.setDistrict(strings.get(9));
                        company.setRegion(regionMap.get(strings.get(9).trim()));
                        company.setCity(strings.get(10));
                        company.setIndustryType(StringHelper.split(file1.getName(),".").get(0));
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/M/d");
                        String dateValue = strings.get(11);
                        if(!StringHelper.isNullValue(dateValue)){
                            LocalDate date = LocalDate.from(dateTimeFormatter.parse(dateValue));
                            company.setEstablishment(date);
                        }
                        System.out.println(company);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setFundsAndFundsType(String fileName,String value, Company company){
        if(StringHelper.isNotEmpty(value)){
            String[] values = value.split("万");
            System.out.println(fileName+"--"+value);
            company.setFunds(new BigDecimal(values[0]));
            if(values[1].contains("人民币")){
                company.setFundsType("￥");
            }else{
                company.setFundsType("$");
            }
        }
    }
}
