package com.example.autocodetemplate.service;

import com.example.autocodetemplate.domain.OcrRecResult;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public interface OcrImageRecognitionService {

    /**
     * 获取OCR拍照识别信息
     * @param imaUrl
     * @return
     */
    OcrRecResult getOcrImageRec(String imaUrl);
}
