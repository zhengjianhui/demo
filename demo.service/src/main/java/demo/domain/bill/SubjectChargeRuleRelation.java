package demo.domain.bill;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import demo.enums.SubjectType;

public class SubjectChargeRuleRelation implements Serializable {
    private Long id;

    private Long chargeItemId;

    private Long chargeRuleId;

    private SubjectType subjectType;

    private Long subjectId;

    private BigDecimal inputAmount;

    private Date chargeStartDay;

    private Date chargeEndDay;

    private Long operatorId;

    private String operatorName;

    private String remark;

    private static final long serialVersionUID = 1L;

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

    public Long getChargeRuleId() {
        return chargeRuleId;
    }

    public void setChargeRuleId(Long chargeRuleId) {
        this.chargeRuleId = chargeRuleId;
    }

    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType ;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public BigDecimal getInputAmount() {
        return inputAmount;
    }

    public void setInputAmount(BigDecimal inputAmount) {
        this.inputAmount = inputAmount;
    }

    public Date getChargeStartDay() {
        return chargeStartDay;
    }

    public void setChargeStartDay(Date chargeStartDay) {
        this.chargeStartDay = chargeStartDay;
    }

    public Date getChargeEndDay() {
        return chargeEndDay;
    }

    public void setChargeEndDay(Date chargeEndDay) {
        this.chargeEndDay = chargeEndDay;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}