package com.abba.service.impl;

import com.abba.dao.ICompanyDao;
import com.abba.entity.GPSEntity;
import com.abba.entity.request.Pager;
import com.abba.model.po.Company;
import com.abba.service.ICompanyService;
import com.abba.util.CSVReaderHelper;
import com.abba.util.ExcelReadHelper;
import com.abba.util.GPSConverterHelper;
import com.abba.util.GaodeMapHelper;
import com.abba.util.StringHelper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * @author dengbojing
 */
@Service
@Slf4j
public class CompanyServiceImpl implements ICompanyService<Company> {

    private final ICompanyDao<Company> companyDao;

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    public CompanyServiceImpl(ICompanyDao<Company> companyDao) {
        this.companyDao = companyDao;
    }

    private final Map<String,String> regionMap = new HashMap<>(64);
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

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Optional<Integer> add(Company company){
        companyDao.create(company);
        return Optional.of(1);
    }

    @Override
    public Optional<Integer> add(List<Company> list) {
        List<Company> list1 = new ArrayList<>();
        list1.addAll(list);
        Session session = companyDao.getSessionFactory().openSession();
        session.beginTransaction();
        list1.forEach(company->{
            session.save(company);
            /*if(company.getCity().trim().equalsIgnoreCase("重庆市")){
                NativeQuery<Company> query = session.createNativeQuery(" select * from t_company where name ='"+company.getName().trim()+"' and address = '"+company.getAddress().trim()+"'", Company.class);
                if(query.list().size() ==0){

                }
            }else{
                session.save(company);
            }*/
            log.info(JSON.toJSONString(company));
        });
        session.getTransaction().commit();
        log.info("done");
        return Optional.of(list.size());
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void parseLocation() {
        File file = new File("F:\\data\\20190823\\");
        Stream<File> stream = Stream.of(file.listFiles());
        List<Company> list = new ArrayList<>();
        stream.forEach(file1 -> {
            Map<Integer, List<String>> map = null;
            try {
                map = CSVReaderHelper.read(file1.getPath());
                map.forEach((i, strings) -> {
                    if(i != 0){
                        Company company = new Company();
                        company.setName(strings.get(0));
                        company.setAddress(strings.get(1));
                        setFundsAndFundsType(strings.get(2),company);
                        company.setListed(strings.get(3));
                        company.setHighTech(strings.get(4));
                        company.setPatent(strings.get(5));
                        company.setFinancing(strings.get(6));
                        company.setIndividual(strings.get(7));
                        company.setStatus(strings.get(8));
                        /*company.setDistrictName(strings.get(9));
                        company.setRegionName(regionMap.get(strings.get(9).trim()));
                        company.setCityName(strings.get(10));*/
                        company.setIndustryType(StringHelper.split(file1.getName(),".").get(0));
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/M/d");
                        String dateValue = strings.get(11);
                        if(!StringHelper.isNullValue(dateValue)){
                            LocalDate date = LocalDate.from(dateTimeFormatter.parse(dateValue));
                            company.setEstablishment(date);
                        }

                        list.add(company);
                    }
                });
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        });
        for(int i = 0; i < 4; i++){
            int from  = i*1000;
            int to = (i+1)*1000;
            if( to > list.size()){
                to = list.size();
            }
            List<Company> list1 = new ArrayList<>(list.subList(from, to));
            executorService.submit(()->{
                this.add(list1);
            });
        }
    }

    private void setFundsAndFundsType(String value, Company company){
        if(StringHelper.isNotEmpty(value)){
            String[] values = value.split("万");
            company.setFunds(new BigDecimal(values[0]));
            if(values[1].contains("人民币")){
                company.setFundsType("￥");
            }else{
                company.setFundsType("$");
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void locationParse1(Pager pager){
        List<Company> allList = companyDao.sqlQueryList(" select * from t_company where gcjLat is null", null);
        for(int i = 0; i < 5; i++){
            int from = i*5000;
            int to = (i+1)*5000;
            if(to > allList.size()){
                to = allList.size();
            }
            List<Company> list = new ArrayList<>(allList.subList(from,to));
            executorService.submit(()->{
                AtomicInteger atomicInteger = new AtomicInteger(0);
                list.forEach(company -> {
                    String addressStr = company.getAddress();
                    try {
                        String locationInfo = GaodeMapHelper.address2Location(addressStr,company.getCity().getCityName());
                        JSONObject jsonObject = JSON.parseObject(locationInfo);
                        JSONArray jsonArray = jsonObject.getJSONArray("geocodes");
                        String locationStr = "";
                        if(jsonArray != null && jsonArray.size() > 0){
                            locationStr = jsonArray.getJSONObject(0).get("location").toString();
                        }
                        if(StringHelper.isNotEmpty(locationStr)){
                            List<String> locationList = StringHelper.split(locationStr, ",");
                            company.setGcjLat(Double.parseDouble(locationList.get(1)));
                            company.setGcjLon(Double.parseDouble(locationList.get(0)));
                            GPSEntity gps = GPSConverterHelper.gcj_To_Gps84(company.getGcjLat(), company.getGcjLon());
                            company.setWgs84Lat(gps.getLat());
                            company.setWgs84Lon(gps.getLon());
                        }
                        log.info(atomicInteger.getAndIncrement()+""+JSON.toJSONString(company));
                    } catch (IOException e) {
                        log.error(e.getMessage());
                    }
                });
                this.updateCompany(list);
            });
        }


    }

    private Optional<Integer> updateCompany(List list){
        Session session = companyDao.getSessionFactory().openSession();
        session.beginTransaction();
        list.forEach(session::update);
        session.getTransaction().commit();
        log.info("done");
        return Optional.of(list.size());
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Optional<Integer> excelParse2() throws IOException {
        Map<Integer,List<String>> map = ExcelReadHelper.read("F:\\data\\20190822\\待解析.xlsx");
        FileOutputStream fis = new FileOutputStream(new File("F:\\data\\20190822\\chanye.md"),true);
        map.forEach((integer, strings) -> {
            if(integer != 0 && integer != 1){
                String name = strings.get(0);
                Map<Integer,Object> params = new HashMap<>(2);
                params.put(0,name);
                List<Company> companyList = companyDao.sqlQueryList(" select * from t_company where name = ?0", params);
                String location = " ";
                if(!CollectionUtils.isEmpty(companyList)){
                    location = companyList.get(0).getWgs84Lon() + "\t" + companyList.get(0).getWgs84Lat();
                }
                location += "\n";
                try {
                    fis.write(location.getBytes());

                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        });
        fis.flush();
        fis.close();
        log.info("done");
        return Optional.of(1);
    }

}
