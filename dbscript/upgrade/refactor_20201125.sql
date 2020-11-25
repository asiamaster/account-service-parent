-- 帐户增加持卡人信息
ALTER TABLE `dili_account`.`account_user_account` 
ADD COLUMN `hold_name` varchar(40) NULL COMMENT '持卡人姓名' AFTER `customer_contacts_phone`,
ADD COLUMN `hold_certificate_number` varchar(40) NULL COMMENT '持卡人证件号' AFTER `hold_name`,
ADD COLUMN `hold_contacts_phone` varchar(20) NULL COMMENT '持卡人联系电话' AFTER `hold_certificate_number`;