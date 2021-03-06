package com.dili.account.dto;

import com.dili.account.common.annotation.SqlXss;
import com.dili.account.validator.ConstantValidator;
import com.dili.account.common.annotation.IsOrderBy;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Auther: miaoguoxin
 * @Date: 2020/6/19 10:27
 * @Description:
 */
public class BaseDto implements Serializable {
    /** */
	private static final long serialVersionUID = 746716184736078528L;
	/**操作员id*/
    private Long opId;
    /**操作员名字*/
    private String opName;
    /** 操作员工号*/
    private String opNo;
    /** 市场ID*/
    private Long firmId;
    /**页码*/
    @NotNull(message = "页码不能为空", groups = ConstantValidator.Page.class)
    @Min(value = 1, message = "页码最小为1", groups = ConstantValidator.Page.class)
    private Integer page;
    /**每页多少条*/
    @NotNull(message = "分页条数不能为空", groups = ConstantValidator.Page.class)
   // @Range(min = 1, max = 100, message = "每页最少1条，最多100条", groups = ConstantValidator.Page.class)
    private Integer rows;
    /**顺序or降序 ASC、DESC*/
    @IsOrderBy(message = "非法的排序顺序", groups = ConstantValidator.Page.class)
    private String order;
    /**排序字段*/
    @SqlXss(message = "非法的排序字段")
    private String sort;

    public BaseDto setDefSort(String defSort) {
        if (StringUtils.isBlank(this.getSort())) {
            this.setSort(defSort);
        }
        return this;
    }

    public BaseDto setDefOrder(String defColumn) {
        if (StringUtils.isBlank(this.getOrder())) {
            this.setOrder(defColumn);
        }
        return this;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Long getOpId() {
        return opId;
    }

    public void setOpId(Long opId) {
        this.opId = opId;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public String getOpNo() {
        return opNo;
    }

    public void setOpNo(String opNo) {
        this.opNo = opNo;
    }

    public Long getFirmId() {
        return firmId;
    }

    public void setFirmId(Long firmId) {
        this.firmId = firmId;
    }
}
