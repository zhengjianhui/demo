package demo.dto.bill;

import java.math.BigDecimal;
import java.util.Date;

import demo.enums.CalcType;
import demo.enums.ChargeCategory;
import demo.enums.PeriodType;
import demo.enums.SubjectType;

public class SubjectChargeRuleRelationDTO {
    private Long id;

    private Long chargeItemId;

    private String chargeItemName;

    private ChargeCategory chargeCategory;

    private Boolean outerOrg;

    private Boolean alwaysOnePeriod;

    private PeriodType periodType;

    private SubjectType subjectType;

    private Long subjectId;
    
    private String subject;

    private Long chargeRuleId;

    private String chargeRuleName;

    private Boolean calcPerMonth;

    private Integer intervalMonth;

    private CalcType calcType;

    private BigDecimal unitPrice;

    private String countType;

    private BigDecimal fixedAmount;

    private BigDecimal inputAmount;

    private Date chargeStartDay;

    private Date chargeEndDay;

    private Long operatorId;

    private String operatorName;

    private String remark;

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
        this.subjectType = subjectType;
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
        this.operatorName = operatorName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getChargeItemName() {
        return chargeItemName;
    }

    public void setChargeItemName(String chargeItemName) {
        this.chargeItemName = chargeItemName;
    }

    public String getChargeRuleName() {
        return chargeRuleName;
    }

    public void setChargeRuleName(String chargeRuleName) {
        this.chargeRuleName = chargeRuleName;
    }

    public CalcType getCalcType() {
        return calcType;
    }

    public void setCalcType(CalcType calcType) {
        this.calcType = calcType;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCountType() {
        return countType;
    }

    public void setCountType(String countType) {
        this.countType = countType;
    }

    public BigDecimal getFixedAmount() {
        return fixedAmount;
    }

    public void setFixedAmount(BigDecimal fixedAmount) {
        this.fixedAmount = fixedAmount;
    }

    public ChargeCategory getChargeCategory() {
        return chargeCategory;
    }

    public void setChargeCategory(ChargeCategory chargeCategory) {
        this.chargeCategory = chargeCategory;
    }

    public Boolean getOuterOrg() {
        return outerOrg;
    }

    public void setOuterOrg(Boolean outerOrg) {
        this.outerOrg = outerOrg;
    }

    public Boolean getAlwaysOnePeriod() {
        return alwaysOnePeriod;
    }

    public void setAlwaysOnePeriod(Boolean alwaysOnePeriod) {
        this.alwaysOnePeriod = alwaysOnePeriod;
    }

    public PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }

    public Boolean getCalcPerMonth() {
        return calcPerMonth;
    }

    public void setCalcPerMonth(Boolean calcPerMonth) {
        this.calcPerMonth = calcPerMonth;
    }

    public Integer getIntervalMonth() {
        return intervalMonth;
    }

    public void setIntervalMonth(Integer intervalMonth) {
        this.intervalMonth = intervalMonth;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    

}