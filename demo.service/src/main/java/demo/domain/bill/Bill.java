package demo.domain.bill;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import demo.enums.BillCheckStatus;
import demo.enums.BillPayStatus;
import demo.enums.SubjectType;


public class Bill implements Serializable {
    private Long id;

    private Long chargeItemId;

    private SubjectType subjectType;

    private Long subjectId;

    private String subject;

    private Long houseId;

    private Long blockId;

    private Long propertyId;

    private BigDecimal unitPrice;

    private BigDecimal quantity;

    private Date beginDate;

    private Date endDate;

    private BigDecimal fee;

    private BigDecimal lateFee;

    private Long totalBillId;

    private BillCheckStatus checkStatus;

    private BillPayStatus payStatus;

    private Date payTime;

    private Long operatorId;

    private String operatorName;

    private Date createTime;

    private String remark;

    private Boolean prepaidCharge;

    private String prepaidRemark;

    public String getPrepaidRemark() {
        return prepaidRemark;
    }

    public void setPrepaidRemark(String prepaidRemark) {
        this.prepaidRemark = prepaidRemark;
    }

    private static final long serialVersionUID = 1L;

    public Boolean getPrepaidCharge() {
        return prepaidCharge;
    }

    public void setPrepaidCharge(Boolean prepaidCharge) {
        this.prepaidCharge = prepaidCharge;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChargeItemId() {
        return chargeItemId;
    }

    public void setChargeItemId(Long chargeItemId) {
        this.chargeItemId = chargeItemId;
    }

    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getTotalBillId() {
        return totalBillId;
    }

    public void setTotalBillId(Long totalBillId) {
        this.totalBillId = totalBillId;
    }

    public BillCheckStatus getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(BillCheckStatus checkStatus) {
        this.checkStatus = checkStatus;
    }

    public BillPayStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(BillPayStatus payStatus) {
        this.payStatus = payStatus;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Override
    public String toString() {
        return "Bill{" +
            "id=" + id +
            ", chargeItemId=" + chargeItemId +
            ", subjectType=" + subjectType +
            ", subjectId=" + subjectId +
            ", subject='" + subject + '\'' +
            ", houseId=" + houseId +
            ", blockId=" + blockId +
            ", propertyId=" + propertyId +
            ", unitPrice=" + unitPrice +
            ", quantity=" + quantity +
            ", beginDate=" + beginDate +
            ", endDate=" + endDate +
            ", fee=" + fee +
            ", lateFee=" + lateFee +
            ", totalBillId=" + totalBillId +
            ", checkStatus=" + checkStatus +
            ", payStatus=" + payStatus +
            ", payTime=" + payTime +
            ", operatorId=" + operatorId +
            ", operatorName='" + operatorName + '\'' +
            ", createTime=" + createTime +
            ", remark='" + remark + '\'' +
            ", prepaidCharge=" + prepaidCharge +
            ", prepaidRemark='" + prepaidRemark + '\'' +
            '}';
    }
}
