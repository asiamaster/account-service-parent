-- 帐户增加持卡人信息
ALTER TABLE `dili_account`.`account_user_account` 
ADD COLUMN `hold_name` varchar(40) NULL COMMENT '持卡人姓名' AFTER `customer_contacts_phone`,
ADD COLUMN `hold_certificate_number` varchar(40) NULL COMMENT '持卡人证件号' AFTER `hold_name`,
ADD COLUMN `hold_contacts_phone` varchar(20) NULL COMMENT '持卡人联系电话' AFTER `hold_certificate_number`;

-- 账户类型修改数据类型
ALTER TABLE `dili_account`.`account_user_account` 
DROP COLUMN `type`,
CHANGE COLUMN `customer_market_type` `customer_character_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户角色类型-买/经营户/其它等，多值以逗号分隔(冗余customer)' AFTER `customer_code`;

-- 更新寿光买家角色
update account_user_account set customer_character_type = 'buyer_character_type' 
where customer_character_type in('native','in_province','inside_buyer','outside_buyer') and firm_id = 8;
-- 更新寿光经营户角色
update account_user_account set customer_character_type = 'business_user_character_type' 
where customer_character_type in('seller') and firm_id = 8;
-- 更新寿光其它角色
update account_user_account set customer_character_type = 'other_character_type' 
where customer_character_type in('driver') and firm_id = 8;

