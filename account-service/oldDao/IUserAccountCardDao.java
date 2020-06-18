package com.dili.oldAccountDao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


/**
 * 用户信息、账户信息、卡信息联合查询
 * 
 * @author bob<>
 */
@Mapper
public interface IUserAccountCardDao {
//	/**
//	 * 查询用户信息、账户信息、及卡信息，不包括退卡状态的数据
//	 */
//	List<UserAccountCardDto> selectList(UserAccountCardQuery queryParam);
//
//	/**
//	 * 根据accountId或cardNo查询唯一数据,不包括退卡状态的数据
//	 */
//	UserAccountCardDto getOnly(UserAccountCardQuery queryParam);
}
