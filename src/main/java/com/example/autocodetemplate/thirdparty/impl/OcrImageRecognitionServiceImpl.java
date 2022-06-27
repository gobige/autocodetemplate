//package com.example.autocodetemplate.thirdparty.impl;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.example.autocodetemplate.domain.response.OcrRecResult;
//import com.example.autocodetemplate.thirdparty.OcrImageRecognitionService;
//import com.example.autocodetemplate.util.HttpPostUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.DataInputStream;
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * <p>爱车小屋</p>
// * <p>Project: carhouse-xx</p>
// * <p>ModuleID: xx</p>
// * <p>Comments: OCR图片识别</p>
// * <p>JDK version used JDK1.8</p>
// *
// * @version 1.0
// */
//@Service("ocrImageRecognitionService")
//public class OcrImageRecognitionServiceImpl implements OcrImageRecognitionService {
//    private static final Logger logger = LoggerFactory.getLogger(OcrImageRecognitionServiceImpl.class);
//
//
//    @Value(" ${com.yates.thirdparty.aliyun.ocr.AppKey}")
//    private String AppKey;
//    @Value("${com.yates.thirdparty.aliyun.ocr.AppSecret}")
//    private String AppSecret;
//    @Value("${com.yates.thirdparty.aliyun.ocr.AppCode}")
//    private String AppCode;
//    @Value("${com.yates.thirdparty.aliyun.ocr.host}")
//    private String host;
//
//    @Override
//    public OcrRecResult getOcrImageRec(String imaUrl) {
//        OcrRecResult ocrRecResult = new OcrRecResult();
//
//        Map<String, String> headerMap = new HashMap<>();
//        headerMap.put("Authorization", "APPCODE " + AppCode);
//        headerMap.put("Content-Type", "application/json; charset=UTF-8");
//
//        JSONObject paramObj = new JSONObject();
//        try {
//            byte[] byteArray = downloadPicture(imaUrl);
//
//            String strJsonObject =  new sun.misc.BASE64Encoder().encode(byteArray).trim();
//            paramObj.put("image", strJsonObject);
//        } catch (Exception e) {
//            logger.error("获取OCR图片二进制流失败" + e.getMessage());
//            return ocrRecResult;
//        }
//        String strResult = "";
//        try {
//            strResult = HttpPostUtils.doPost(host, paramObj, headerMap);
//
//            ocrRecResult = JSON.parseObject(strResult, OcrRecResult.class);
//            logger.info("result:" + ocrRecResult.toString());
//        } catch (Exception e) {
//            logger.error("请求扫描执照信息失败!!! 阿里云OCR response:{}", strResult); ;
//            logger.error(e.getMessage()); ;
//        }
//
//        return ocrRecResult;
//    }
//
//    public static byte[] downloadPicture(String urlList) throws IOException {
//        try (DataInputStream dataInputStream = new DataInputStream(new URL(urlList).openStream());
//             ByteArrayOutputStream output = new ByteArrayOutputStream()) {
//
//            byte[] buffer = new byte[1024 * 1024];
//            int length;
//            while ((length = dataInputStream.read(buffer)) > 0) {
//                output.write(buffer, 0, length);
//            }
//
//            return output.toByteArray();
//        }
//    }
//
//    public  static String getRecImageBytes(File file) throws IOException{
//        BufferedImage bi;
//        bi = ImageIO.read(file);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(bi, "jpg", baos);
//        byte[] bytes = baos.toByteArray();
//
//        return new sun.misc.BASE64Encoder().encode(bytes).trim();
//    }
//}
