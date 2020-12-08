-- 帐户增加持卡人信息
ALTER TABLE `dili_account`.`account_user_account` 
ADD COLUMN `hold_name` varchar(40) NULL COMMENT '持卡人姓名' AFTER `customer_contacts_phone`,
ADD COLUMN `hold_certificate_number` varchar(40) NULL COMMENT '持卡人证件号' AFTER `hold_name`,
ADD COLUMN `hold_contacts_phone` varchar(20) NULL COMMENT '持卡人联系电话' AFTER `hold_certificate_number`;

-- 账户类型修改数据类型
ALTER TABLE `dili_account`.`account_user_account` 
CHANGE COLUMN `type` `types` varchar(50) NULL DEFAULT NULL COMMENT '账户类型-买/经营户/其它等，可多选，以逗号分隔' AFTER `card_exist`;