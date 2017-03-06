package demo.dao.mybatis.db2.bill;

import org.springframework.stereotype.Repository;

import java.util.List;

import demo.dto.bill.ParkingSpaceChargeRuleRelationConditionDTO;
import demo.dto.bill.SubjectChargeRuleRelationDTO;


@Repository
public interface SubjectChargeRuleRelationMapper {
//    int deleteByPrimaryKey(Long id);
//
//    int insert(SubjectChargeRuleRelation record);
//
//    void insertBatch(List<SubjectChargeRuleRelation> subjectChargeRuleRelations);
//
//    SubjectChargeRuleRelation selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(SubjectChargeRuleRelation record);
//
//    int updateByPrimaryKey(SubjectChargeRuleRelation record);
//
//    List<SubjectChargeRuleRelation> selectBySubjectId(Long subjectId);
//
//    List<SubjectChargeRuleRelationDTO> selectBySubjectIdAndChargeItemId(@Param("subjectId") Long subjectId, @Param("chargeItemId") Long chargeItemId);
//
//    List<SubjectChargeRuleRelation> selectByChargeRuleId(@Param("chargeRuleId") Long chargeRuleId);
//
//    void batchRemoveParkingSpaceChargeRuleRelation(BatchRemoveParkingSpaceChargeRuleRelationDTO dto);
//
//    void batchRemoveStoreroomChargeRuleRelation(BatchRemoveStoreroomChargeRuleRelationDTO dto);
//
//    void batchRemoveOuterORGChargeRuleRelation(BatchRemoveOuterORGChargeRuleRelationDTO dto);
//
//    void batchRemoveHouseChargeRuleRelation(BatchRemoveHouseChargeRuleRelationDTO batchRemovehouseChargeRuleRelationDTO);
//
//    void batchUpdateHouseChargeRuleRelationChargeStartDay(BatchUpdateHouseChargeRuleRelationStartDayDTO batchUpdateHouseChargeRuleRelationStartDayDTO);
//
//    void batchUpdateParkingSpaceChargeRuleRelationChargeStartDay(BatchUpdateParkingSpaceChargeRuleRelationStartDayDTO batchUpdateParkingSpaceChargeRuleRelationStartDayDTO);
//
//    void batchUpdateHouseChargeRuleRelationChargeEndDay(BatchUpdateHouseChargeRuleRelationEndDayDTO batchUpdateHouseChargeRuleRelationEndDayDTO);
//
//    void batchUpdateStoreroomChargeRuleRelationChargeStartDay(BatchUpdateStoreroomChargeRuleRelationStartDayDTO batchUpdateStoreroomChargeRuleRelationStartDayDTO);
//
//    void batchUpdateOuterORGChargeRuleRelationChargeStartDay(BatchUpdateOuterORGChargeRuleRelationStartDayDTO batchUpdateOuterORGChargeRuleRelationStartDayDTO);
//
//    void batchUpdateStoreroomChargeRuleRelationChargeEndDay(BatchUpdateStoreroomChargeRuleRelationEndDayDTO batchUpdateStoreroomChargeRuleRelationEndDayDTO);
//
//    void batchUpdateOuterORGChargeRuleRelationChargeEndDay(BatchUpdateOuterORGChargeRuleRelationEndDayDTO batchUpdateOuterORGChargeRuleRelationEndDayDTO);
//
//    void batchUpdateParkingSpaceChargeRuleRelationChargeEndDay(BatchUpdateParkingSpaceChargeRuleRelationEndDayDTO batchUpdateParkingSpaceChargeRuleRelationEndDayDTO);
//
//    List<SubjectChargeRuleRelationDTO> selectHouseChargeRuleRelations(HouseChargeRuleRelationConditionDTO houseChargeRuleRelationConditionDTO);

    List<SubjectChargeRuleRelationDTO> selectParkingSpaceChargeRuleRelations(
            ParkingSpaceChargeRuleRelationConditionDTO parkingSpaceChargeRuleRelationConditionDTO);
//
//    List<SubjectChargeRuleRelationDTO> selectStoreroomChargeRuleRelations(
//            StoreroomChargeRuleRelationConditionDTO storeroomChargeRuleRelationConditionDTO);
//
//    List<SubjectChargeRuleRelationDTO> selectOuterORGChargeRuleRelations(
//            OuterORGChargeRuleRelationConditionDTO outerORGChargeRuleRelationConditionDTO);
//
//    List<SubjectChargeRuleRelationDTO> selectBySubjectIds(List<Long> subjectIdList);
//
//    List<SubjectChargeRuleRelationDTO> selectParkingSpaceChargeRuleRelationsByHosueCondition(HouseChargeRuleRelationConditionDTO houseChargeRuleRelationConditionDTO);
//
//    Page<SubjectChargeRuleRelationDTO> selectHouseChargeRuleRelationsPageable(@Param("conditionDTO") HouseChargeRuleRelationConditionDTO conditionDTO, Pageable pageable);
//
//    Page<SubjectChargeRuleRelationDTO> selectParkingSpaceChargeRuleRelationsPageable(@Param("conditionDTO") ParkingSpaceChargeRuleRelationConditionDTO conditionDTO, Pageable pageable);
//
//    Page<SubjectChargeRuleRelationDTO> selectStoreroomChargeRuleRelationsPageable(@Param("conditionDTO") StoreroomChargeRuleRelationConditionDTO storeroomChargeRuleRelationConditionDTO, Pageable pageable);
//
//    Page<SubjectChargeRuleRelationDTO> selectOuterORGChargeRuleRelationsPageable(@Param("conditionDTO") OuterORGChargeRuleRelationConditionDTO outerORGChargeRuleRelationConditionDTO, Pageable pageable);
//
//    List<SubjectChargeRuleRelationDTO> selectStoreroomChargeRuleRelationsByHosueCondition(HouseChargeRuleRelationConditionDTO houseChargeRuleRelationConditionDTO);
}
