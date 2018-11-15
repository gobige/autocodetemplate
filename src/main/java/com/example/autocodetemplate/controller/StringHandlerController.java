package com.example.autocodetemplate.controller;

import com.example.autocodetemplate.controller.vo.AutoFillSqlVO;
import com.example.autocodetemplate.controller.vo.AutoGenerateGetSetVO;
import com.example.autocodetemplate.service.TestService;
import com.example.autocodetemplate.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.ws.RequestWrapper;
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
@RestController
@RequestMapping("/context/handle")
public class StringHandlerController {

    private final Logger logger = LoggerFactory.getLogger(GenerateTemplateController.class);

    @Resource
    private TestService testService;

    @RequestMapping(value = "testMethodExec.json")
    public  Map<String, Object> test(@RequestParam("task") Integer task) throws Exception{
        Map<String, Object> result = new HashMap<>();

        if (task == 1) {
            testService.doTaskOne();
        } else if (task == 2) {
            testService.doTaskTwo();
        } else if (task == 3) {
            testService.doTaskThree();
        }

        result.put("code",0);
        result.put("bcode",0);
        return result;
    }

    @ApiOperation(value = "格式化多行sql为单行",notes = "格式化多行sql为单行")
    @ResponseBody
    @RequestMapping("sql/multi/convert.json")
    public Map<String, Object> formatSqlToSingleLine(@RequestParam() String mutiSqlStr) {
        Map<String, Object> result = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();

        if (!subject.isAuthenticated()) {
            result.put("code",0);
            result.put("bcode",1);
            result.put("mes","you not autority！please login");
            return result;
        }

        String singleSqlStr = StringUtil.formatSqlToSingleLine(mutiSqlStr);
        result.put("singleSqlStr", singleSqlStr);
        result.put("code",0);
        result.put("bcode",0);

        return result;
    }


    @ApiOperation(value = "将占位参数填充进预编译sql中", notes = "将占位参数填充进预编译sql中")
    @ResponseBody
    @RequestMapping("presql/fill.json.json")
    public Map<String, Object> autoFillSql(@RequestBody() AutoFillSqlVO autoFillSqlVO) {
        Map<String, Object> result = new HashMap<>();
        if (autoFillSqlVO == null) {
            result.put("code",0);
            result.put("bcode",1);
            result.put("mes","参数不能为null");
            return result;
        }
        autoFillSqlVO.setPlaceholder(StringUtils.isEmpty(autoFillSqlVO.getPlaceholder()) ? "\\?" : autoFillSqlVO.getPlaceholder());
        autoFillSqlVO.setSplitSymbol(StringUtils.isEmpty(autoFillSqlVO.getSplitSymbol()) ? "\\," : autoFillSqlVO.getSplitSymbol());
        String singleSqlStr = StringUtil.autoFillSql(autoFillSqlVO.getReplaceParamStr(),autoFillSqlVO.getSplitSymbol(),autoFillSqlVO.getPreSql(),autoFillSqlVO.getPlaceholder());

        result.put("code",0);
        result.put("bcode",0);
        result.put("singleSqlStr", singleSqlStr);

        return result;
    }


    @ApiOperation(value = "自动生成get，set语句",notes = "自动生成get，set语句")
    @ResponseBody
    @RequestMapping("value/assign/generate.json")
    public Map<String, Object> autoGenerateGetSetByVariable(@RequestBody() AutoGenerateGetSetVO autoGenerateGetSetVO) {
        Map<String, Object> result = new HashMap<>();

        if (autoGenerateGetSetVO == null) {
            result.put("code",0);
            result.put("bcode",1);
            result.put("mes","参数不能为null");
            return result;
        }
        String singleSqlStr = StringUtil.autoGenerateGetSetByVariable(autoGenerateGetSetVO.getGetObjClassNamesource(), autoGenerateGetSetVO.getSetObjClassNametarget(), autoGenerateGetSetVO.getSourcePath(), autoGenerateGetSetVO.getStringContent());
        result.put("code",0);
        result.put("bcode",0);
        result.put("singleSqlStr", singleSqlStr);

        return result;
    }

}
