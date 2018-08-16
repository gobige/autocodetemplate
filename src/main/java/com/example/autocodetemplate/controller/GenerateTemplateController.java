package com.example.autocodetemplate.controller;

import com.example.autocodetemplate.domain.TableStructure;
import com.example.autocodetemplate.filter.GenerateTempFilter;
import com.example.autocodetemplate.service.GenerateTemplateService;
import com.example.autocodetemplate.service.TargetTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
@Controller
@RequestMapping("/yates/template")
public class GenerateTemplateController {

    private final Logger logger = LoggerFactory.getLogger(GenerateTemplateController.class);

    @Resource
    private GenerateTemplateService generateTemplateService;
    @Resource
    private TargetTableService targetTableService;

    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String dbUserName;
    @Value("${spring.datasource.password}")
    private String dbPassword;
    @Value("${spring.datasource.driverClassName}")
    private String jdbcDriver;


    /**
     *
     * @param tempFilter
     * @return
     */
    @RequestMapping(value = "getTemp.json", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> generateTemp(@RequestBody() GenerateTempFilter tempFilter) {
        Map<String, Object> result = new HashMap<>();
        tempFilter.setDbPassword(dbPassword);
        tempFilter.setDbUrl(dbUrl);
        tempFilter.setDbUserName(dbUserName);
        tempFilter.setJdbcDriver(jdbcDriver);
//        Collection<TableStructure> tableStructures = targetTableService.descTableStru(tempFilter);
        Collection<TableStructure> tableStructures = targetTableService.jdbcGetTableStru(tempFilter);

        for(TableStructure tableStructure : tableStructures) {
            if(!StringUtils.isEmpty(tableStructure.getType()) && tableStructure.getType().indexOf("(") > 0) {
                tableStructure.setType(tableStructure.getType().substring(0,tableStructure.getType().indexOf("(")));
            }
        }
        boolean success = generateTemplateService.generateTemplateByTable(tempFilter.getTableName(),tableStructures);

        if(success) {
            result.put("code","0");
            result.put("bMessage","success");
        }else {
            result.put("code","1");
            result.put("bMessage","failure");
        }

        return result;
    }

}
