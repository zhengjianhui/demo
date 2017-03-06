package demo.dto.bill;

import java.util.List;

import demo.enums.PropertyRightType;


public class ParkingSpaceChargeRuleRelationConditionDTO {
    private Long blockId;
    private Long parkingLotId;
    private PropertyRightType spaceType;
    private Long parkingSpaceId;
    private Long chargeItemId;
    private Long chargeRuleId;
    private List<Long> chargeRuleRelationIds;

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    public Long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public PropertyRightType getSpaceType() {
        return spaceType;
    }

    public void setSpaceType(PropertyRightType spaceType) {
        this.spaceType = spaceType;
    }

    public Long getParkingSpaceId() {
        return parkingSpaceId;
    }

    public void setParkingSpaceId(Long parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId;
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

    public List<Long> getChargeRuleRelationIds() {
        return chargeRuleRelationIds;
    }

    public void setChargeRuleRelationIds(List<Long> chargeRuleRelationIds) {
        this.chargeRuleRelationIds = chargeRuleRelationIds;
    }

}
