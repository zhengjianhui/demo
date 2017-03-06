package demo.dao.mybatis.db2.bill;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import demo.domain.bill.SubjectChargeItemRelation;
import demo.dto.bill.ParkingSpaceQueryConditionDTO;


@Repository
public interface SubjectChargeItemRelationMapper {
//    int deleteByPrimaryKey(Long id);
//
//    int delBySubjectIdAndChargeItemId(@Param("subjectId") Long subjectId, @Param("chargeItemId") Long chargeItemId);
//
//    int insert(SubjectChargeItemRelation record);
//
//    int insertBatch(List<SubjectChargeItemRelation> subjectChargeItemRelationList);
//
//    SubjectChargeItemRelation selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(SubjectChargeItemRelation record);
//
//    int updateByPrimaryKey(SubjectChargeItemRelation record);
//
//    List<SubjectChargeItemRelation> selectBySubjectIdAndChargeItemId(@Param("subjectId") Long subjectId, @Param("chargeItemId") Long chargeItemId);
//
//    void updateFeeExpireDate(@Param("subjectId") Long subjectId, @Param("chargeItemId") Long chargeItemId, @Param("feeExpireDate") Date endDate);
//
//    void updateFeeDiscount(@Param("subjectId") Long subjectId, @Param("chargeItemId") Long chargeItemId, @Param("feeDiscount") BigDecimal feeDiscount);
//
//    List<SubjectChargeItemRelation> selectHouseChargeItemRelation(@Param("dto") HouseQueryConditionDTO hosueQueryCondition, @Param("chargeItemId") Long chargeItemId);
//
//    List<SubjectChargeItemRelation> selectParkingSpaceChargeItemRelation(@Param("dto") HouseQueryConditionDTO hosueQueryCondition, @Param("chargeItemId") Long chargeItemId);

    List<SubjectChargeItemRelation> selectParkingSpaceChargeItemRelationWithoutHouse(@Param("dto") ParkingSpaceQueryConditionDTO parkingSpaceQueryConditionDTO, @Param("chargeItemId") Long chargeItemId);

//    List<SubjectChargeItemRelation> selecStoreroomChargeItemRelation(@Param("dto") HouseQueryConditionDTO hosueQueryCondition, @Param("chargeItemId") Long chargeItemId);
//
//    List<SubjectChargeItemRelation> selecStoreroomChargeItemRelationWithoutHouse(@Param("dto") StoreroomQueryConditionDTO storeroomQueryConditionDTO, @Param("chargeItemId") Long chargeItemId);
//
//    List<SubjectChargeItemRelation> selecOuterORGChargeItemRelationWithoutHouse(@Param("dto") OuterORGQueryConditionDTO outerORGQueryConditionDTO, @Param("chargeItemId") Long chargeItemId);
}
