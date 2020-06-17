package com.dili.account.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: miaoguoxin
 * @Date: 2020/6/17 10:46
 * @Description: 卡信息
 */
public class UserCardResponseDto implements Serializable {
    /** 卡ID */
    private Long cardId;
    /** 卡号 */
    private String cardNo;
    /** 使用权限(充值、提现、交费等) */
    private List<String> permissionList;
    /** 卡类别-主/副/临时/联营 */
    private Integer category;
    /** 卡片状态 {@link com.dili.account.type.CardStatus} */
    private Integer state;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public List<String> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
