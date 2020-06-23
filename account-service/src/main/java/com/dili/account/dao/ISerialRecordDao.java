package com.dili.account.dao;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.dili.account.entity.SerialRecordDo;

/**
 * 业务端账户流水
 * @author bob<>
 */
@Mapper
public interface ISerialRecordDao {
	/**
     * 列表查询
     * @param page
     * @param search
     * @return
     */
	List<SerialRecordDo> selectList(SerialRecordDo serialRecord);

    /**
     * 新增
     * @param serialRecord
     * @return
     */
	int save(SerialRecordDo serialRecord);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	SerialRecordDo getById(Long id);

    /**
     * 修改
     * @param serialRecord
     * @return
     */
	int update(SerialRecordDo serialRecord);

    /**
     * 删除
     * @param id
     * @return
     */
	int batchRemove(Long[] id);
}