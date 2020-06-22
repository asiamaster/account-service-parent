package com.dili.account.service.impl;

import com.dili.account.dao.ISerialRecordDao;
import com.dili.account.entity.SerialRecordDo;
import com.dili.account.service.ISerialRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 操作流水记录service实现类
 * @author xuliang
 */
@Service
public class SerialRecordServiceImpl implements ISerialRecordService {

    @Resource
    private ISerialRecordDao serialRecordDao;

    @Override
    public void save(SerialRecordDo serialRecord) {
        serialRecordDao.save(serialRecord);
    }

    @Override
    public void batchSave(List<SerialRecordDo> serialRecordList) {
        serialRecordDao.batchSave(serialRecordList);
    }
}
