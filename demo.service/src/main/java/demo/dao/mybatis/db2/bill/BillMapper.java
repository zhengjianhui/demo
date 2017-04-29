package demo.dao.mybatis.db2.bill;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import demo.domain.bill.Bill;

@Repository
public interface BillMapper {

//    int insert(Bill record);
//
//    void addBatch(List<Bill> records);
//
//    int updateByPrimaryKeySelective(Bill record);
//
//    int deleteByPrimaryKey(Long id);
//
//    void deleteBatch(BatchRemoveBillDTO batchRemoveBillDTO);
//
//    Bill selectByPrimaryKey(Long id);
//
//    List<Bill> selectBySubjectId(@Param("subjectType") SubjectType subjectType, @Param("subjectId") Long subjectId, @Param("chargeItemId") Long chargeItemId,
//                                 @Param("checkStatus") BillCheckStatus checkStatus, @Param("payStatus") BillPayStatus payStatus);
//
//    List<Bill> selectByHouseId(@Param("houseId") Long houseId, @Param("checkStatus") BillCheckStatus checkStatus);
//
//    List<Bill> selectByTotalBillId(@Param("totalBillId") Long totalBillId);
//
//    void checkBill(@Param("totalBillId") Long totalBillId, @Param("checkStatus") BillCheckStatus checkStatus, @Param("payTime") Date payTime);
//
//    int updateStatusAndPayTime(Bill record);
//
//    List<Bill> quickSearch(@Param("totalBillId") Long totalBillId, @Param("blockFieldId") Long blockFieldId, @Param("buildingId") Long buildingId,
//                           @Param("houseId") Long houseId);
//
//    Page<Bill> quickSearchPageable(@Param("totalBillId") Long totalBillId, @Param("blockFieldId") Long blockFieldId, @Param("buildingId") Long buildingId,
//                                   @Param("houseId") Long houseId, Pageable pageable);
//
//    void pay(@Param("billIdList") List<Long> billIdList, @Param("payStatus") BillPayStatus payStatus, @Param("payTime") Date payTime);
//
//    void batchPrepaidPay(@Param("billIdList") List<Long> billIdList, @Param("payStatus") BillPayStatus payStatus, @Param("payTime") Date payTime,
//                         @Param("prepaidCharge") Boolean prepaidCharge, @Param("prepaidRemark") String prepaidRemark);
//
//    void cancelPrepaidPay(Long billId);
//
//    boolean existPayFinishBill(BatchRemoveBillDTO batchRemoveBillDTO);
//
//    List<Bill> selectHouseUnpaidBills(@Param("buildingId") Long buildingId, @Param("chargeItemId") Long chargeItemId, @Param("chargeEndDay") Date chargeEndDay);
//
//    List<Bill> selectParkingSpaceUnpaidBills(@Param("buildingId") Long buildingId, @Param("chargeItemId") Long chargeItemId,
//                                             @Param("chargeEndDay") Date chargeEndDay);

    List<Bill> selectParkingSpaceUnpaidBillsWithoutHouse(@Param("parkingLotId") Long parkingLotId, @Param("chargeItemId") Long chargeItemId,
                                                         @Param("chargeEndDay") LocalDateTime chargeEndDay);
//
//    List<Bill> selectStoreroomUnpaidBills(@Param("buildingId") Long buildingId, @Param("chargeItemId") Long chargeItemId,
//                                          @Param("chargeEndDay") Date chargeEndDay);
//
//    List<Bill> selectStoreroomUnpaidBillsWithoutHouse(@Param("storeroomFildId") Long storeroomFildId, @Param("chargeItemId") Long chargeItemId,
//                                                      @Param("chargeEndDay") Date chargeEndDay);
//
//    List<Bill> selectOuterORGUnpaidBillsWithoutHouse(@Param("blockId") Long blockId, @Param("chargeItemId") Long chargeItemId,
//                                                     @Param("chargeEndDay") Date chargeEndDay);
//
//    List<Bill> querySubjectBillByPrepaidPay(@Param("subjectId") Long subjectId, @Param("prepaidCharge") Boolean prepaidCharge);
//
//    List<Bill> selectbatchByCondition(QueryBillConditions queryConditionsDTO);

}
