package com.dili.account.controller;

import cn.hutool.core.collection.CollUtil;
import com.dili.account.dto.SerialDto;
import com.dili.account.entity.SerialRecordDo;
import com.dili.account.service.ISerialRecordService;
import com.dili.ss.domain.BaseOutput;
import com.dili.ss.domain.PageOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
     * @param serialDto
     * @return
     */
    @RequestMapping(value = "/batchSave")
    public BaseOutput<?> save(@RequestBody SerialDto serialDto) {
        try {
            if (CollUtil.isEmpty(serialDto.getSerialRecordList())) {
                return BaseOutput.failure("操作流水记录列表为空");
            }
            serialRecordService.batchSave(serialDto.getSerialRecordList());
            return BaseOutput.success();
        } catch (Exception e) {
            LOGGER.error("save", e);
            return BaseOutput.failure();
        }
    }

    /**
     * 分页查询操作流水列表
     * @param serialDto
     * @return
     */
    @RequestMapping(value = "/listPage")
    public PageOutput<List<SerialRecordDo>> listPage(@RequestBody SerialDto serialDto) {
        try {
            return serialRecordService.listPage(serialDto);
        } catch (Exception e) {
            LOGGER.error("listPage", e);
            return PageOutput.failure();
        }
    }
}
