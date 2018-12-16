package com.xiaobaby.myproject.vo;

/**
 * @author Lu Yufeng
 * @date 2018/7/16 下午2:25
 */
public class OffLineDataEntryVO {
    private Long id;

    private Integer reportDate;

    private Integer dataType;

    private String dataTypeName;

    private String storeId;

    private String cityId;

    private Integer type;

    private String typeName;

    private Integer subType;

    private String subTypeName;

    private Integer status;

    private Double amount;

    private String keyList;

    private String valueList;

    private String creator;

    private Long createTime;

    private Long updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getReportDate() {
        return reportDate;
    }

    public void setReportDate(Integer reportDate) {
        this.reportDate = reportDate;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getDataTypeName() {
        return dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public String getSubTypeName() {
        return subTypeName;
    }

    public void setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getKeyList() {
        return keyList;
    }

    public void setKeyList(String keyList) {
        this.keyList = keyList;
    }

    public String getValueList() {
        return valueList;
    }

    public void setValueList(String valueList) {
        this.valueList = valueList;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "OffLineDataEntryVO{" +
                "id=" + id +
                ", reportDate=" + reportDate +
                ", dataType=" + dataType +
                ", dataTypeName='" + dataTypeName + '\'' +
                ", storeId='" + storeId + '\'' +
                ", cityId='" + cityId + '\'' +
                ", type=" + type +
                ", typeName='" + typeName + '\'' +
                ", subType=" + subType +
                ", subTypeName='" + subTypeName + '\'' +
                ", status=" + status +
                ", amount=" + amount +
                ", keyList='" + keyList + '\'' +
                ", valueList='" + valueList + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
