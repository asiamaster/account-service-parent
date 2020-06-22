package com.dili.account.controller;

import cn.hutool.core.collection.CollUtil;
import com.dili.account.dto.SerialRequestDto;
import com.dili.account.service.ISerialRecordService;
import com.dili.ss.domain.BaseOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 操作流水api
 * @author xuliang
 */
@RestController
@RequestMapping(value = "/api/serial")
public class SerialRecordController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SerialRecordController.class);

    @Resource
    private ISerialRecordService serialRecordService;
    /**
     * 批量存储流水记录
     * @param serialRequestDto
     * @return
     */
    @RequestMapping(value = "/batchSave")
    public BaseOutput<?> save(@RequestBody SerialRequestDto serialRequestDto) {
        try {
            if (CollUtil.isEmpty(serialRequestDto.getSerialRecordList())) {
                return BaseOutput.failure("操作流水记录列表为空");
            }
            serialRecordService.batchSave(serialRequestDto.getSerialRecordList());
            return BaseOutput.success();
        } catch (Exception e) {
            LOGGER.error("save", e);
            return BaseOutput.failure();
        }
    }
}
