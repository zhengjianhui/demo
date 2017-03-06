package demo.domain.bill;

import java.io.Serializable;

import demo.enums.ChargeCategory;
import demo.enums.PeriodType;
import demo.enums.SubjectType;


public class ChargeItem implements Serializable {
    private Long id;

    private String name;

    private ChargeCategory category;

    private SubjectType subjectType;

    private Boolean outerOrg;

    private Boolean alwaysOnePeriod;

    private PeriodType periodType;

    private Long blockId;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public ChargeCategory getCategory() {
        return category;
    }

    public void setCategory(ChargeCategory category) {
        this.category = category;
    }

    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
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

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}