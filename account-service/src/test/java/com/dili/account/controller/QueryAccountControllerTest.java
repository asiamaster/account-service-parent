package com.dili.account.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dili.account.BaseTest;
import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.dto.UserAccountCardResponseDto;
import com.dili.ss.domain.BaseOutput;
import com.dili.ss.domain.PageOutput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
        String cardNo = "24944459289";
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

    @Test
    void testGetPage() throws Exception {
        UserAccountCardQuery param = createQueryParamDate();
        param.setPageNum(1);
        param.setPageSize(1);
        String resultJson = mockMvc.perform(post("/api/account/getPage")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(param))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        PageOutput<List<UserAccountCardResponseDto>> page = JSON.parseObject(resultJson,new TypeReference<>(){});
        assertTrue(page.isSuccess());
    }

    @Test
    void getList() {
    }
}
