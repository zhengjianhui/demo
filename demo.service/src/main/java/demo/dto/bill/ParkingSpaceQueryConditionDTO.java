package demo.dto.bill;

import java.util.List;

import demo.enums.PropertyRightType;
import demo.enums.SpaceStatus;


public class ParkingSpaceQueryConditionDTO {
    private Long blockId;
    private Long parkingLotId;
    private PropertyRightType spaceType;
    private String no;
    private Boolean hasRelationHouse;
    private List<Long> parkingSpaceIdList;
    private SpaceStatus status;



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

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public List<Long> getParkingSpaceIdList() {
        return parkingSpaceIdList;
    }

    public void setParkingSpaceIdList(List<Long> parkingSpaceIdList) {
        this.parkingSpaceIdList = parkingSpaceIdList;
    }

    public Boolean getHasRelationHouse() {
        return hasRelationHouse;
    }

    public void setHasRelationHouse(Boolean hasRelationHouse) {
        this.hasRelationHouse = hasRelationHouse;
    }

    public SpaceStatus getStatus() {
        return status;
    }

    public void setStatus(SpaceStatus status) {
        this.status = status;
    }

}
