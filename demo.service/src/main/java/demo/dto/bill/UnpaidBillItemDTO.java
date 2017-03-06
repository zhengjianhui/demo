package demo.dto.bill;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "待缴费账单明细对象")
public class UnpaidBillItemDTO {

    @ApiModelProperty(value = "起始缴费日期", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;

    @ApiModelProperty(value = "缴费至日期", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @ApiModelProperty(value = "金额计算公式", required = true)
    private String feeCalcFormula;

    @ApiModelProperty(value = "单价", required = false)
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "数量", required = false)
    private BigDecimal quantity;

    @ApiModelProperty(value = "折扣", required = false)
    private BigDecimal feeDiscount;

    @ApiModelProperty(value = "合计金额", required = false)
    private BigDecimal fee;

    @ApiModelProperty(value = "应付金额", required = false)
    private BigDecimal payAmount;

    @ApiModelProperty(value = "优惠金额", required = false)
    private BigDecimal exemptAmount = BigDecimal.ZERO;

    @ApiModelProperty(value = "滞纳金金额", required = false)
    private BigDecimal lateFee = BigDecimal.ZERO;

    public String getFeeCalcFormula() {
        return feeCalcFormula;
    }

    public void setFeeCalcFormula(String feeCalcFormula) {
        this.feeCalcFormula = feeCalcFormula;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
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

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getExemptAmount() {
        return exemptAmount;
    }

    public void setExemptAmount(BigDecimal exemptAmount) {
        this.exemptAmount = exemptAmount;
    }

    public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    public BigDecimal getFeeDiscount() {
        return feeDiscount;
    }

    public void setFeeDiscount(BigDecimal feeDiscount) {
        this.feeDiscount = feeDiscount;
    }

}