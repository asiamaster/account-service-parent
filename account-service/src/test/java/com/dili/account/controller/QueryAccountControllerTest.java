package com.dili.account.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dili.account.BaseTest;
import com.dili.account.dto.UserAccountCardResponseDto;
import com.dili.http.okhttp.utils.B;
import com.dili.ss.domain.BaseOutput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * @Auther: miaoguoxin
 * @Date: 2020/6/18 15:37
 * @Description:
 */
class QueryAccountControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getOneAccountCard() throws Exception {
        String cardNo ="2494445928";
        MvcResult mvcResult = mockMvc.perform(get("/api/account/getOneAccountCard/{cardNo}", cardNo)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String resultContent = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        BaseOutput<UserAccountCardResponseDto> out = JSON.parseObject(resultContent, new TypeReference<>() {
        });
        assertTrue(out.isSuccess());
        assertNotNull(out.getData());
    }
}
