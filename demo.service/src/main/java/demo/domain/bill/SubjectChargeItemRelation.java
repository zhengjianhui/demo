package demo.domain.bill;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import demo.enums.SubjectType;


public class SubjectChargeItemRelation implements Serializable {
    private Long id;

    private Long chargeItemId;

    private SubjectType subjectType;

    private Long subjectId;

    private BigDecimal feeDiscount;

    private Date feeExpireDate;

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

    public BigDecimal getFeeDiscount() {
        return feeDiscount;
    }

    public void setFeeDiscount(BigDecimal feeDiscount) {
        this.feeDiscount = feeDiscount;
    }

    public Date getFeeExpireDate() {
        return feeExpireDate;
    }

    public void setFeeExpireDate(Date feeExpireDate) {
        this.feeExpireDate = feeExpireDate;
    }
}