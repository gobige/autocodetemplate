package com.example.autocodetemplate.controller;

import com.example.autocodetemplate.domain.TableStructure;
import com.example.autocodetemplate.exception.ServiceRuntimeException;
import com.example.autocodetemplate.filter.GenerateTempFilter;
import com.example.autocodetemplate.service.GenerateTemplateService;
import com.example.autocodetemplate.service.TargetTableService;
import io.swagger.annotations.ApiOperation;
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
import java.util.ArrayList;
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
    // http://localhost:8080/swagger-ui.html 访问swwager文档
    @ApiOperation(value = "通过表名生成java代码模板",notes = "生成domain，mapper，service，dao")
    @RequestMapping(value = "getTemp.json", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> generateTemp(@RequestBody() GenerateTempFilter tempFilter) throws ServiceRuntimeException {
        Map<String, Object> result = new HashMap<>();
        tempFilter.setDbPassword(StringUtils.isEmpty(tempFilter.getDbPassword()) ? dbPassword : tempFilter.getDbPassword());
        tempFilter.setDbUrl(StringUtils.isEmpty(tempFilter.getDbUrl()) ? dbUrl : tempFilter.getDbUrl());
        tempFilter.setDbUserName(StringUtils.isEmpty(tempFilter.getDbUserName()) ? dbUserName : tempFilter.getDbUserName());
        tempFilter.setJdbcDriver(StringUtils.isEmpty(tempFilter.getJdbcDriver()) ? jdbcDriver : tempFilter.getJdbcDriver());
//        Collection<TableStructure> tableStructures = targetTableService.descTableStru(tempFilter);
        Collection<TableStructure> tableStructures = new ArrayList<>();
        tableStructures = targetTableService.jdbcGetTableStru(tempFilter);

        for(TableStructure tableStructure : tableStructures) {
            if(!StringUtils.isEmpty(tableStructure.getType()) && tableStructure.getType().indexOf("(") > 0) {
                tableStructure.setType(tableStructure.getType().substring(0,tableStructure.getType().indexOf("(")));
            }
        }
        logger.info("开始执行生成代码");
        try {
            boolean success = generateTemplateService.generateTemplateByTable(tempFilter.getTableName(),tableStructures);
        } catch (Exception e) {
            logger.error("生成代码失败{}",e.getMessage());
            throw new ServiceRuntimeException("自动生成模板出错");

        }
        logger.info("生成代码结束");

        return result;
    }

}
