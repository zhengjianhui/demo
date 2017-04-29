package demo.dto.bill;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import demo.enums.ChargeCategory;
import demo.enums.SubjectType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "待缴费账单对象")
public class UnpaidBillDTO {
    @ApiModelProperty(value = "缴费主体类型", required = true)
    private SubjectType subjectType;

    @ApiModelProperty(value = "缴费主体id", required = true)
    private Long subjectId;

    @ApiModelProperty(value = "缴费主体名称", required = true)
    private String subject;

    @ApiModelProperty(value = "缴费项id", required = true)
    private Long chargeItemId;

    @ApiModelProperty(value = "缴费项名称", required = false)
    private String chargeItemName;

    @ApiModelProperty(value = "缴费项分类", required = false)
    private ChargeCategory chargeCategory;

    @ApiModelProperty(value = "费用截止日期", required = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate feeExpireDate;

    @ApiModelProperty(value = "待缴费明细", required = true)
    private List<UnpaidBillItemDTO> billItems = new ArrayList<>();

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
        this.subject = subject;
    }

    public Long getChargeItemId() {
        return chargeItemId;
    }

    public void setChargeItemId(Long chargeItemId) {
        this.chargeItemId = chargeItemId;
    }

    public String getChargeItemName() {
        return chargeItemName;
    }

    public void setChargeItemName(String chargeItemName) {
        this.chargeItemName = chargeItemName;
    }

    public ChargeCategory getChargeCategory() {
        return chargeCategory;
    }

    public void setChargeCategory(ChargeCategory chargeCategory) {
        this.chargeCategory = chargeCategory;
    }

    public LocalDate getFeeExpireDate() {
        return feeExpireDate;
    }

    public void setFeeExpireDate(LocalDate feeExpireDate) {
        this.feeExpireDate = feeExpireDate;
    }

    public List<UnpaidBillItemDTO> getBillItems() {
        return billItems;
    }

    public void setBillItems(List<UnpaidBillItemDTO> billItems) {
        this.billItems = billItems;
    }

}