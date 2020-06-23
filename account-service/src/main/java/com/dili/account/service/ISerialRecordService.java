package com.dili.account.service;

import com.dili.account.entity.SerialRecordDo;

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
}
