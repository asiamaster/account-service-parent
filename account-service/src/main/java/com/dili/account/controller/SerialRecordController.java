package com.dili.account.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.dili.account.dto.SerialQueryDto;
import com.dili.account.entity.SerialRecordDo;
import com.dili.account.service.ISerialRecordService;
import com.dili.ss.domain.BaseOutput;
import com.dili.ss.domain.PageOutput;

import cn.hutool.core.collection.CollUtil;

/**
 * 操作流水api
 * 
 * @author xuliang
 */
@RestController
@RequestMapping(value = "/api/serial")
public class SerialRecordController {

	private static final Logger log = LoggerFactory.getLogger(SerialRecordController.class);

	@Resource
	private ISerialRecordService serialRecordService;

	/**
	 * 批量存储流水记录
	 * 
	 * @param serialRecordDoList
	 * @return
	 */
	@RequestMapping(value = "/batchSave")
	public BaseOutput<?> save(@RequestBody List<SerialRecordDo> serialRecordDoList) {
		log.info("批量存储流水记录*****{}", JSONObject.toJSONString(serialRecordDoList));
		if (CollUtil.isEmpty(serialRecordDoList)) {
			return BaseOutput.failure("操作流水记录列表为空");
		}
		serialRecordService.batchSave(serialRecordDoList);
		return BaseOutput.success();
	}

	/**
	 * 分页查询操作流水列表
	 * 
	 * @param serialQueryDto
	 * @return
	 */
	@RequestMapping(value = "/listPage")
	public PageOutput<List<SerialRecordDo>> listPage(@RequestBody SerialQueryDto serialQueryDto) {
		log.info("分页查询操作流水列表*****{}", JSONObject.toJSONString(serialQueryDto));
		return serialRecordService.listPage(serialQueryDto);
	}
}
