package demo.dao.mybatis.db2.bill;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import demo.domain.bill.ParkingSpace;
import demo.dto.bill.ParkingSpaceQueryConditionDTO;
import demo.enums.ParkingLotType;


@Repository
public interface ParkingSpaceMapper {
//    int deleteByPrimaryKey(Long id);
//
//    int insert(ParkingSpace record);
//
//    void insertBatch(List<ParkingSpace> artition);
//
//    ParkingSpace selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(ParkingSpace record);
//
////    int updateByPrimaryKey(ParkingSpace record);
//
//    List<ParkingSpace> selectByParkingLotId(Long parkingLotId);
//
//    List<ParkingSpace> selectByRelationHouseId(Long relationHouseId);
//
//    Long selectHouseHasParkingNumber(Long relationHouseId);
//
//    void deleteByBlockId(Long blockId);
//
//
//    Long selectParkingSpaceCount(List<Long> blockIds);
//
//    List<ParkingSpace> selectByBlockId(Long blockId);
//
//    void updateParkingLotType(@Param("parkingLotId") Long parkingLotId, @Param("parkingLotType") ParkingLotType newParkingLotType);
//
//    void updateParkingLotName(@Param("parkingLotId") Long parkingLotId, @Param("parkingLotName") String newParkingLotName);
//
//    ParkingSpace selectByNo(@Param("parkingLotId") Long parkingLotId, @Param("no") String no);
//
//    List<ParkingSpace> selectByOwnerId(Long ownerId);
//
//    List<SimpleParkingSpaceDTO> selectSimpleParkingSpace(@Param("dto") ParkingSpaceQueryConditionDTO parkingSpaceQueryCondition);

    List<ParkingSpace> quickSearch(@Param("dto") ParkingSpaceQueryConditionDTO parkingSpaceQueryConditionDTO);

//    Page<ParkingSpace> quickSearchPageable(@Param("dto") ParkingSpaceQueryConditionDTO parkingSpaceQueryCondition, Pageable pageable);
//
//    void clearRelationHouseId(Long id);
//
//    ParkingSpace selectByParkingLotIdAndNo(@Param("parkingLotId") Long parkingLotId, @Param("no") String no);
//
//    List<ParkingSpace> selectByBuildingId(@Param("buildingId") Long buildingId);
//
//    void updateLinkUserId(@Param("id") Long id, @Param("linkUserId") Long linkUserId);
//
//    void updateRelationHouseId(@Param("id") Long id, @Param("relationHouseId") Long relationHouseId);
//
//    void updateRemark(@Param("id") Long id, @Param("remark") String remark);
//
//    List<BuildingChargeSubjectInfoDTO> selectBuildingParkingSpaceInfo(@Param("blockId") Long blockId);
//
//    List<ParkingSpace> selectByHouseCondition(HouseQueryConditionDTO houseQueryCondition);
//
//    List<ParkingSpaceLastPaymentInfoDTO> selectParkingSpaceLastPaymentInfo(@Param("parkingLotId") Long parkingLotId, @Param("chargeItemId") Long chargeItemId);
//
//    List<ParkingSpaceCountDTO> selectParkingSpaceCountByBlockIds(List<Long> blockIds);
//
//    void updateSpaceTypeBatch(@Param("dto") ParkingSpaceQueryConditionDTO spaceQueryConditionDTO, @Param("spaceType") PropertyRightType spaceType);
//
//    void updateSpaceStatusBatch(@Param("dto") ParkingSpaceQueryConditionDTO spaceQueryConditionDTO, @Param("spaceStatus") SpaceStatus spaceStatus);
//
//    void updateAreaBatch(@Param("dto") ParkingSpaceQueryConditionDTO spaceQueryConditionDTO, @Param("area") BigDecimal area);
//
//    void updateSellTimeBatch(@Param("dto") ParkingSpaceQueryConditionDTO spaceQueryConditionDTO, @Param("sellTime") Date sellTime);
//
//    void updateRemarkBatch(@Param("dto") ParkingSpaceQueryConditionDTO spaceQueryConditionDTO, @Param("remark") String remark);
//
//    void updateParkingLotInfoBatch(@Param("dto") ParkingSpaceQueryConditionDTO spaceQueryConditionDTO, @Param("parkingLotId") Long parkingLotId, @Param("parkingLotName") String parkingLotName, @Param("parkingLotType") ParkingLotType parkingLotType);
//


}
