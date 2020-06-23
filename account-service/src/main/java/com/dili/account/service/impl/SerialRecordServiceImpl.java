package com.dili.account.service.impl;

import com.dili.account.dao.ISerialRecordDao;
import com.dili.account.dto.SerialDto;
import com.dili.account.entity.SerialRecordDo;
import com.dili.account.service.ISerialRecordService;
import com.dili.ss.domain.PageOutput;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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


    @Override
    public PageOutput<List<SerialRecordDo>> listPage(SerialDto serialDto) {
        PageHelper.startPage(serialDto.getPage(), serialDto.getRows());
        List<SerialRecordDo> itemList = serialRecordDao.list(serialDto);
        Page<SerialRecordDo> page = (Page)itemList;
        PageOutput<List<SerialRecordDo>> output = PageOutput.success();
        output.setData(itemList);
        output.setPageNum(page.getPageNum());
        output.setPageSize(page.getPageSize());
        output.setTotal(Long.valueOf(page.getTotal()).intValue());
        output.setStartRow(page.getStartRow());
        output.setEndRow(page.getEndRow());
        output.setPages(page.getPages());
        return output;
    }
}