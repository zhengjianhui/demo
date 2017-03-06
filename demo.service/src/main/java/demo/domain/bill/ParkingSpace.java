package demo.domain.bill;


import com.google.common.base.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import demo.enums.ParkingLotType;
import demo.enums.PropertyRightType;
import demo.enums.SpaceStatus;
import demo.enums.SubjectType;

public class ParkingSpace implements Subject, Comparable<ParkingSpace>, Serializable {
    private Long id;

    private String no;

    private Long parkingLotId;

    private ParkingLotType parkingLotType;

    private String parkingLotName;

    private Long blockId;

    private PropertyRightType spaceType;

    private BigDecimal area;

    private Long relationHouseId;

    private String carNum;

    private Long linkUserId;

    private String remark;

    private SpaceStatus status;

    private Date sellTime;

    private static final long serialVersionUID = 1L;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public Long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    @Override
    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    public PropertyRightType getSpaceType() {
        return spaceType;
    }

    public void setSpaceType(PropertyRightType spaceType) {
        this.spaceType = spaceType;
    }

    public ParkingLotType getParkingLotType() {
        return parkingLotType;
    }

    public void setParkingLotType(ParkingLotType parkingLotType) {
        this.parkingLotType = parkingLotType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public Long getRelationHouseId() {
        return relationHouseId;
    }

    public void setRelationHouseId(Long relationHouseId) {
        this.relationHouseId = relationHouseId;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public Long getLinkUserId() {
        return linkUserId;
    }

    public void setLinkUserId(Long linkUserId) {
        this.linkUserId = linkUserId;
    }

    public String getFullName() {
        if (StringUtils.isNotBlank(parkingLotName)) {
            return this.parkingLotName.trim() + StringUtils.trimToEmpty(this.no);
        } else {
            return StringUtils.trimToEmpty(this.no);
        }
    }

    @Override
    public int compareTo(ParkingSpace o) {
        if (!Objects.equal(this.getParkingLotId(), o.getParkingLotId())) {
            if (NumberUtils.isDigits(this.getParkingLotName()) && NumberUtils.isDigits(o.getParkingLotName())) {
                long a = Long.parseLong(this.getParkingLotName());
                long b = Long.parseLong(o.getParkingLotName());
                if (a != b) {
                    return a > b ? 1 : -1;
                }
            } else {
                int i = this.getParkingLotName().compareTo(o.getParkingLotName());
                if (i != 0) {
                    return i;
                }
            }
        }

        if (!Objects.equal(this.getNo(), o.getNo())) {
            if (NumberUtils.isDigits(this.getNo()) && NumberUtils.isDigits(o.getNo())) {
                long a = Long.parseLong(this.getNo());
                long b = Long.parseLong(o.getNo());
                if (a != b) {
                    return a > b ? 1 : -1;
                } else {
                    return 0;
                }
            } else {
                return this.getNo().compareTo(o.getNo());
            }
        }

        return 0;
    }

    @Override
    public String getName() {
        return getFullName();
    }

    @Override
    public SubjectType getSubjectType() {
        return SubjectType.PARKINGSPACE;
    }

    public SpaceStatus getStatus() {
        return status;
    }

    public void setStatus(SpaceStatus status) {
        this.status = status;
    }

    public Date getSellTime() {
        return sellTime;
    }

    public void setSellTime(Date sellTime) {
        this.sellTime = sellTime;
    }

}
