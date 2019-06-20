package com.example.autocodetemplate.controller;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

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
public class GenerateTemplateControllerTest {
    @Resource
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getTempletTest() throws Exception {
        JSONObject param = new JSONObject();
        param.put("tableName", "wst_activity_item_ext_presale");

        // 准备http报文 header body
        RequestBuilder request = MockMvcRequestBuilders
                .post("/yates/template/getTemp.json")
                .contentType("application/json;charset=UTF-8")
                .content(param.toString());

        MvcResult result = mockMvc.perform(request).andReturn();
        Assert.assertTrue("状态200", result.getResponse().getStatus() == 200);
        Assert.assertFalse("状态不是200" + result.getResponse().getStatus(), result.getResponse().getStatus() != 200);

        System.out.println(result.getResponse().getContentAsString());
    }
}
