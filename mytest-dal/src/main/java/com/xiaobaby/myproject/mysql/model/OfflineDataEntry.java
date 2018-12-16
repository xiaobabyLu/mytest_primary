package com.xiaobaby.myproject.mysql.model;

public class OfflineDataEntry {
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
        this.dataTypeName = dataTypeName == null ? null : dataTypeName.trim();
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId == null ? null : storeId.trim();
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
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
        this.typeName = typeName == null ? null : typeName.trim();
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
        this.subTypeName = subTypeName == null ? null : subTypeName.trim();
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
        this.keyList = keyList == null ? null : keyList.trim();
    }

    public String getValueList() {
        return valueList;
    }

    public void setValueList(String valueList) {
        this.valueList = valueList == null ? null : valueList.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
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
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", reportDate=").append(reportDate);
        sb.append(", dataType=").append(dataType);
        sb.append(", dataTypeName=").append(dataTypeName);
        sb.append(", storeId=").append(storeId);
        sb.append(", cityId=").append(cityId);
        sb.append(", type=").append(type);
        sb.append(", typeName=").append(typeName);
        sb.append(", subType=").append(subType);
        sb.append(", subTypeName=").append(subTypeName);
        sb.append(", status=").append(status);
        sb.append(", amount=").append(amount);
        sb.append(", keyList=").append(keyList);
        sb.append(", valueList=").append(valueList);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}