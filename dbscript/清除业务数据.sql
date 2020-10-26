-- dili_account
use dili_account;
-- 卡片库存
delete from account_card_storage;
-- 账户业务流水
delete from account_serial_record;
-- 用户账户
delete from account_user_account;
-- 用户卡片
delete from account_user_card;
-- seata
delete from undo_log;