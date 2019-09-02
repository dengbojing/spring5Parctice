package com.abba.controller;

import com.abba.entity.vo.Pager;
import com.abba.model.Company;
import com.abba.service.ICompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author dengbojing
 */
@RestController
@RequestMapping("company")
@Slf4j
public class CompanyController {


    @Autowired
    private ICompanyService<Company> companyService;


    @GetMapping("/excel/parse")
    public String companyAdd(){
        companyService.parseLocation();
        return "success";
    }

    @GetMapping("/location/parse")
    public String companyParse(){
        Pager pager = new Pager();
        pager.setPageSize(1000);
        companyService.locationParse1(pager);
        return "success";
    }

    @GetMapping("/location/parse2")
    public String companyParse2() throws IOException {
        Pager pager = new Pager();
        pager.setPageSize(1000);
        companyService.excelParse2();
        return "success";
    }


}
