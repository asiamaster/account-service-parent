package com.dili.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dili.account.entity.UserCardDo;

/**
 * 用户卡片信息（包括电子卡）
 * @author bob<>
 */
public interface IUserCardDao {
    /**
     * 列表查询
     * @param page
     * @param search
     * @return
     */
    List<UserCardDo> selectList(UserCardDo userCard);

    /**
     * 新增
     * @param userCard
     * @return
     */
    int save(UserCardDo userCard);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    UserCardDo getById(Long id);

    /**
     * 根据账户id查询(已排除退卡状态)
     * @author miaoguoxin
     * @date 2020/6/18
     */
    UserCardDo getByAccountId(Long accountId);

    /**
     * 根据卡号查询(可选择性排除退换状态 needReturn==:0 :查询全部状态 ；needReturn==1：排除退换状态)
     * @author miaoguoxin
     * @date 2020/6/18
     */
    UserCardDo getByCardNo(@Param("cardNo") String cardNo, @Param("needReturn") Integer needReturn);


    /**
     * 修改
     * @param userCard
     * @return
     */
    int update(UserCardDo userCard);

    /**
     * 删除
     * @param id
     * @return
     */
    int batchRemove(Long[] id);

    /**
     * 更新卡状态
     * @param accountId 卡账号
     * @param state
     * @param version
     * @return
     */
    int updateStateById(@Param("id") Long id,
                    @Param("state") Integer state,
                    @Param("version") Integer version);
}
