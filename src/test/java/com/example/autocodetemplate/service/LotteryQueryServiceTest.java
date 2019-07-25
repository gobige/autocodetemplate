package com.example.autocodetemplate.service;

import com.example.autocodetemplate.Enum.EnumLotteryQueryType;
import com.example.autocodetemplate.domain.AreaAndPostCodeResult;
import com.example.autocodetemplate.domain.OcrRecResult;
import com.example.autocodetemplate.exception.ServiceRuntimeException;
import com.example.autocodetemplate.thirdparty.ShgoldQueryService;
import com.example.autocodetemplate.thirdparty.param.req.LotteryQueryJuheRequest;
import com.example.autocodetemplate.thirdparty.param.req.ShgoldQueryJuheRequest;
import com.example.autocodetemplate.thirdparty.param.resp.LotteryQueryJuheResponse;
import com.example.autocodetemplate.thirdparty.AreaCodeAndPostCodeServcie;
import com.example.autocodetemplate.thirdparty.LotteryQueryService;
import com.example.autocodetemplate.thirdparty.OcrImageRecognitionService;
import com.example.autocodetemplate.thirdparty.param.resp.ShgoldQueryJuheResponse;
import com.example.autocodetemplate.util.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LotteryQueryServiceTest {
    @Resource
    private LotteryQueryService lotteryQueryService;
    @Resource
    private OcrImageRecognitionService ocrImageRecognitionService;
    @Resource
    private AreaCodeAndPostCodeServcie areaCodeAndPostCodeServcie;
    @Resource
    private ShgoldQueryService shgoldQueryService;


    @Test
    public void testGetGoldResult()  throws ServiceRuntimeException {
        ShgoldQueryJuheRequest request = new ShgoldQueryJuheRequest();

        ShgoldQueryJuheResponse response = shgoldQueryService.getGoldResults(request);

    }

    @Test
    public void testGetssqResult()  throws ServiceRuntimeException {
        LotteryQueryJuheRequest request = new LotteryQueryJuheRequest();
        request.setLottery_id(EnumLotteryQueryType.SSQ.getKey());
        request.setLottery_no("18085");

        LotteryQueryJuheResponse response = lotteryQueryService.getLotteryResults(request);
        if (response != null) {
            if (response.getResult() != null) {
                System.out.println(response.getResult());
            }
        }
    }

    @Test
    public void testGetOcrImageRec()  throws ServiceRuntimeException {
        OcrRecResult response = ocrImageRecognitionService.getOcrImageRec("http://img.car-house.cn/private/business/licenseSTORE/28333/20170627/b4236e0f179c40a19f2e14ec1bda0739.jpg");

        System.out.println(response.toString());
    }

    @Test
    public void testGetAreaCodeAndPostCode()  throws Exception {
        String fileContent = FileUtil.fileInputStreamToString("c:/暂存/areaName.txt");
        fileContent = fileContent.replaceAll("\\r\\n", ",");

        String[] str = fileContent.split(",");
        List<AreaAndPostCodeResult> responses = new ArrayList<>();
        for (String s : str) {
            try {
                List<AreaAndPostCodeResult> response = getByName(s);
                responses.addAll(response);
            } catch (Exception e) {
                System.out.println("##############################################################################################:" + s);
            }
        }

        for (AreaAndPostCodeResult result : responses) {
            System.out.println(result.toString());
        }
    }

    @Test
    public void testGetSql()  throws Exception {
        String fileContent = FileUtil.fileInputStreamToString("c:/暂存/省市区号和邮编.txt");
        fileContent = fileContent.replaceAll("\\r\\n", ":");

        String[] str = fileContent.split(":");
         for (String s : str) {
            try {
                Integer areaIndex = s.indexOf("area");
                Integer areaStartIndex = areaIndex + 6;
                Integer areaEndIndex = s.indexOf(",",areaStartIndex);
                String area = s.substring(areaStartIndex, areaEndIndex-1);

                Integer postcodeIndex = s.indexOf("postCode");
                Integer postcodeStartIndex = postcodeIndex + 10;
                Integer postcodeEndIndex = s.indexOf("'",postcodeStartIndex);
                String postCode = s.substring(postcodeStartIndex, postcodeEndIndex);

                Integer areaCodeIndex = s.indexOf("areaCode");
                Integer areaCodeStartIndex = areaCodeIndex + 10;
                Integer areaCodeEndIndex = s.indexOf("'",areaCodeStartIndex);
                String areaCode = s.substring(areaCodeStartIndex, areaCodeEndIndex);

//                Integer postCodeIndex = s.indexOf("postCode");
//                Integer areaCodeIndex = s.indexOf("areaCode");
//                List<AreaAndPostCodeResult> response = getByName(s);
//                responses.addAll(response);
                System.out.println("UPDATE wst_areas SET areaCode = '"+areaCode+"',postal_code ="+postCode+" WHERE areaName = '"+area+"';");
            } catch (Exception e) {
                System.out.println("##############################################################################################:" + s);
            }
        }

    }


    private List<AreaAndPostCodeResult> getByName(String name) throws Exception{
        List<AreaAndPostCodeResult> response = areaCodeAndPostCodeServcie.getAreaCodeAndPostCode(name);

        return response;
    }
}
