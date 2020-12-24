package com.dili.account.service;

import com.dili.account.dto.SerialQueryDto;
import com.dili.account.entity.SerialRecordDo;
import com.dili.ss.domain.PageOutput;

import java.util.List;

/**
 * 操作流水记录service接口
 * @author xuliang
 */
public interface ISerialRecordService {

    /**
     * 新增
     * @param serialRecord
     */
    void save(SerialRecordDo serialRecord);

    /**
     * 批量新增
     */
    void batchSave(List<SerialRecordDo> serialRecordList);

    /**
     * 分页查询
     * @param serialQueryDto
     * @return
     */
    PageOutput<List<SerialRecordDo>> listPage(SerialQueryDto serialQueryDto);
    /**
     * 列表查询
     * @param serialQueryDto
     * @return
     */
    List<SerialRecordDo> list(SerialQueryDto serialQueryDto);
    /**
     * 操作金额合计
     * @param serialQueryDto
     * @return
     */
    Long countOperateAmount(SerialQueryDto serialQueryDto);
}
