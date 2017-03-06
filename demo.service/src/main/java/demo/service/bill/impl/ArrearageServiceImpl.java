package demo.service.bill.impl;

import org.apache.commons.lang3.BooleanUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StopWatch;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.dao.mybatis.db2.bill.BillMapper;
import demo.dao.mybatis.db2.bill.ChargeItemMapper;
import demo.dao.mybatis.db2.bill.ParkingSpaceMapper;
import demo.dao.mybatis.db2.bill.SubjectChargeItemRelationMapper;
import demo.dao.mybatis.db2.bill.SubjectChargeRuleRelationMapper;
import demo.domain.bill.Bill;
import demo.domain.bill.ChargeItem;
import demo.domain.bill.ParkingSpace;
import demo.domain.bill.Subject;
import demo.domain.bill.SubjectChargeItemRelation;
import demo.dto.bill.ParkingSpaceChargeRuleRelationConditionDTO;
import demo.dto.bill.ParkingSpaceQueryConditionDTO;
import demo.dto.bill.SubjectChargeRuleRelationDTO;
import demo.dto.bill.UnpaidBillDTO;
import demo.dto.bill.UnpaidBillItemDTO;
import demo.enums.CalcType;
import demo.enums.PeriodType;
import demo.service.bill.ArrearageService;
import demo.util.JSONUtil.JSONUtil;


/**
 * Created by zhengjianhui on 17/3/6.
 */
@Service
public class ArrearageServiceImpl implements ArrearageService {
    private static final Logger logger = LoggerFactory.getLogger(ArrearageServiceImpl.class);

    @Autowired
    private ParkingSpaceMapper parkingSpaceMapper;

    @Autowired
    private ChargeItemMapper chargeItemMapper;

    @Autowired
    private SubjectChargeRuleRelationMapper subjectChargeRuleRelationMapper;

    @Autowired
    private SubjectChargeItemRelationMapper subjectChargeItemRelationMapper;

    @Autowired
    private BillMapper billMapper;


    @Override
    public Map<Long, List<UnpaidBillDTO>> getParkingLotAllUnpaidBill(Long blockId, Long parkingLotId, Long chargeItemId, boolean needCalcAmount,
                                                                     Date chargeEndDay) {
        ParkingSpaceQueryConditionDTO query = new ParkingSpaceQueryConditionDTO();
        query.setBlockId(blockId);
        query.setParkingLotId(parkingLotId);

        List<ParkingSpace> parkingSpaces = parkingSpaceMapper.quickSearch(query);
        if (CollectionUtils.isEmpty(parkingSpaces)) {
            return Collections.emptyMap();
        }
        ParkingSpaceChargeRuleRelationConditionDTO subjectChargeRuleConditionDTO = new ParkingSpaceChargeRuleRelationConditionDTO();
        subjectChargeRuleConditionDTO.setBlockId(blockId);
        subjectChargeRuleConditionDTO.setChargeItemId(chargeItemId);
        subjectChargeRuleConditionDTO.setParkingLotId(parkingLotId);

        Map<Long, List<UnpaidBillDTO>> result = new HashMap<>();
        // 获取小区收费项信息
        List<ChargeItem> chargeItems = chargeItemMapper.selectByBlockId(blockId);
        Map<Long, ChargeItem> chargeItemMap = new HashMap<>();
        for (ChargeItem chargeItem : chargeItems) {
            chargeItemMap.put(chargeItem.getId(), chargeItem);
        }

        if (!CollectionUtils.isEmpty(parkingSpaces)) {
            Map<Long, Subject> parkingSpaceMap = new HashMap<>(parkingSpaces.size());
            for (ParkingSpace parkingSpace : parkingSpaces) {
                parkingSpaceMap.put(parkingSpace.getId(), parkingSpace);
            }

            List<UnpaidBillDTO> parkingSpaceUnpaidBills = null;

            List<SubjectChargeRuleRelationDTO> parkingSpaceChargeRules = subjectChargeRuleRelationMapper.selectParkingSpaceChargeRuleRelations(subjectChargeRuleConditionDTO);

            if (!CollectionUtils.isEmpty(parkingSpaceChargeRules)) {

                List<SubjectChargeItemRelation> parkingSpaceChargeItems = subjectChargeItemRelationMapper.selectParkingSpaceChargeItemRelationWithoutHouse(query, chargeItemId);

                Map<String, SubjectChargeItemRelation> parkingSpaceChargeItemMap = new HashMap<>(parkingSpaceChargeItems.size());

                for (SubjectChargeItemRelation subjectChargeItemRelation : parkingSpaceChargeItems) {
                    parkingSpaceChargeItemMap.put(subjectChargeItemRelation.getSubjectId() + "" + subjectChargeItemRelation.getChargeItemId(),
                            subjectChargeItemRelation);
                }

                parkingSpaceUnpaidBills = calcSubjectUnpaidBills(parkingSpaceChargeRules, parkingSpaceChargeItemMap, parkingSpaceMap, needCalcAmount,
                        chargeEndDay);
            }

            // 获取未支付账单
            List<Bill> parkingSpaceBills = billMapper.selectParkingSpaceUnpaidBillsWithoutHouse(parkingLotId, chargeItemId, chargeEndDay);
            Map<String, UnpaidBillDTO> parkingSpaceChargeitemUnpaidBillMap = calcSubjectUnpaidBills(parkingSpaceBills, chargeItemMap);
            if (parkingSpaceUnpaidBills == null) {
                parkingSpaceUnpaidBills = new ArrayList<>(parkingSpaceChargeitemUnpaidBillMap.size());
            }
            parkingSpaceUnpaidBills.addAll(parkingSpaceChargeitemUnpaidBillMap.values());

            if (!CollectionUtils.isEmpty(parkingSpaceUnpaidBills)) {
                for (UnpaidBillDTO unpaidBillDTO : parkingSpaceUnpaidBills) {
                    ParkingSpace parkingSpace = (ParkingSpace) parkingSpaceMap.get(unpaidBillDTO.getSubjectId());
                    List<UnpaidBillDTO> list = result.get(parkingSpace.getId());
                    if (list == null) {
                        list = new ArrayList<>();
                        result.put(parkingSpace.getId(), list);
                    }
                    list.add(unpaidBillDTO);
                }
            }

        }
        return result;
    }


    private List<UnpaidBillDTO> calcSubjectUnpaidBills(List<SubjectChargeRuleRelationDTO> subjectChargeRuleList,
                                                       Map<String, SubjectChargeItemRelation> subjectChargeItemMap, Map<Long, Subject> subjectMap, boolean needCalcAmount, Date chargeEndDay) {
        DateTime plan2ChargeEndDate = null;
        if (chargeEndDay != null) {
            plan2ChargeEndDate = new DateTime(chargeEndDay);
        } else {
            plan2ChargeEndDate = new DateTime(System.currentTimeMillis());
        }

        Map<String, UnpaidBillDTO> subjectChargeItemUnpaidBillMap = new HashMap<>(subjectChargeRuleList.size());
        for (SubjectChargeRuleRelationDTO subjectChargeRuleRelationDTO : subjectChargeRuleList) {
            String subjectChargeItemId = subjectChargeRuleRelationDTO.getSubjectId() + "" + subjectChargeRuleRelationDTO.getChargeItemId();
            SubjectChargeItemRelation subjectChargeItem = subjectChargeItemMap.get(subjectChargeItemId);
            Subject subject = needCalcAmount ? subjectMap.get(subjectChargeRuleRelationDTO.getSubjectId()) : null;
            DateTime plan2ChargeStartDate = null;
            Date feeExpireDate = null;
            BigDecimal feeDiscount = null;
            if (subjectChargeItem != null) {
                feeExpireDate = subjectChargeItem.getFeeExpireDate();
                plan2ChargeStartDate = new DateTime(feeExpireDate.getTime()).plusDays(1);
                feeDiscount = subjectChargeItem.getFeeDiscount();
            }

            StopWatch sw = new StopWatch();
            sw.start();

            UnpaidBillItemDTO unpaidBillItemDTO =
                    generateUnpaidBillItemDTO(subject, subjectChargeRuleRelationDTO, feeDiscount, plan2ChargeStartDate,
                            plan2ChargeEndDate, needCalcAmount, false);

            sw.stop();
            logger.info("计算单个收费规则花费时长：{} ms", sw.getTotalTimeMillis());

            if (unpaidBillItemDTO != null) {
                UnpaidBillDTO unpaidBillDTO = subjectChargeItemUnpaidBillMap.get(subjectChargeItemId);
                if (unpaidBillDTO == null) {
                    unpaidBillDTO = new UnpaidBillDTO();
                    unpaidBillDTO.setSubjectType(subjectChargeRuleRelationDTO.getSubjectType());
                    unpaidBillDTO.setSubjectId(subjectChargeRuleRelationDTO.getSubjectId());
                    unpaidBillDTO.setSubject(subjectChargeRuleRelationDTO.getSubject());
                    unpaidBillDTO.setChargeItemId(subjectChargeRuleRelationDTO.getChargeItemId());
                    unpaidBillDTO.setChargeItemName(subjectChargeRuleRelationDTO.getChargeItemName());
                    unpaidBillDTO.setChargeCategory(subjectChargeRuleRelationDTO.getChargeCategory());
                    unpaidBillDTO.setFeeExpireDate(feeExpireDate);

                    subjectChargeItemUnpaidBillMap.put(subjectChargeItemId, unpaidBillDTO);
                }
                unpaidBillDTO.getBillItems().add(unpaidBillItemDTO);
            }
        }

        List<UnpaidBillDTO> result = new ArrayList<>(subjectChargeItemUnpaidBillMap.size());
        result.addAll(subjectChargeItemUnpaidBillMap.values());

        return result;
    }

    private Map<String, UnpaidBillDTO> calcSubjectUnpaidBills(List<Bill> bills, Map<Long, ChargeItem> chargeItemMap) {
        Map<String, UnpaidBillDTO> subjectChargeitemUnpaidBillMap = new HashMap<>(bills.size());

        for (Bill bill : bills) {
            String key = bill.getSubjectId() + "" + bill.getChargeItemId();
            UnpaidBillDTO dto = subjectChargeitemUnpaidBillMap.get(key);
            if (dto == null) {
                dto = new UnpaidBillDTO();
                BeanUtils.copyProperties(bill, dto);
                ChargeItem chargeItem = chargeItemMap.get(bill.getChargeItemId());
                dto.setChargeItemName(chargeItem.getName());
                dto.setChargeCategory(chargeItem.getCategory());
                subjectChargeitemUnpaidBillMap.put(key, dto);
            }

            UnpaidBillItemDTO billItemDto = new UnpaidBillItemDTO();
            BeanUtils.copyProperties(bill, billItemDto);
            billItemDto.setPayAmount(bill.getFee());
            List<UnpaidBillItemDTO> billItems = dto.getBillItems();
            if (billItems == null) {
                billItems = new ArrayList<>();
                dto.setBillItems(billItems);
            }
            billItems.add(billItemDto);
        }
        return subjectChargeitemUnpaidBillMap;
    }

    /**
     * @param subject              缴费主体
     * @param subjectChargeRuleDTO 收费项匹配
     * @param feeDiscount          费用折扣
     * @param plan2ChargeStartDate 支付开始时间（以该计划实际收费项结束时间为开始时间）
     * @param plan2ChargeEndDate   费用结束时间 不能为空
     * @param planCharge           是否计算计划应收金额，如果是的话,收费时段不会超出plan2ChargeEndDate
     */
    private UnpaidBillItemDTO generateUnpaidBillItemDTO(Subject subject, SubjectChargeRuleRelationDTO subjectChargeRuleDTO, BigDecimal feeDiscount,
                                                        DateTime plan2ChargeStartDate, DateTime plan2ChargeEndDate, boolean needCalcAmount, boolean planCharge) {
        logger.info("plan2ChargeStartDate:{}  plan2ChargeEndDate:{}", plan2ChargeStartDate, plan2ChargeEndDate);

        // 计算费用收取时间段
        UnpaidBillItemDTO unpaidBillItemDTO = getPayDateRange(subjectChargeRuleDTO, plan2ChargeStartDate, plan2ChargeEndDate, planCharge);
        if (unpaidBillItemDTO == null) {
            return null;
        }

        // 计算费用金额
        if (needCalcAmount) {
            DateTime startDate = new DateTime(unpaidBillItemDTO.getBeginDate());
            DateTime endDate = new DateTime(unpaidBillItemDTO.getEndDate());
            calcFee(subject, subjectChargeRuleDTO, feeDiscount, startDate, endDate, unpaidBillItemDTO);
        }

        return unpaidBillItemDTO;
    }


    private UnpaidBillItemDTO getPayDateRange(SubjectChargeRuleRelationDTO subjectChargeRuleDTO, DateTime plan2ChargeStartDate, DateTime plan2ChargeEndDate,
                                              boolean planCharge) {
        DateTime startDate = null;
        DateTime endDate = null;

        DateTime chargeRuleStartDay = new DateTime(subjectChargeRuleDTO.getChargeStartDay());

        // 收费规则开始时间在计划收费截止时间之后，收费规则未生效；当前收下期没考虑进去
        if (chargeRuleStartDay.isAfter(plan2ChargeEndDate)) {
            return null;
        }

        // 获取开始缴费时间：如果规则中的开始收费时间早于该费用项的费用过期时间，则已费用过期时间为开始时间，否则已费用规则中的开始收费时间为准
        if (plan2ChargeStartDate != null && plan2ChargeStartDate.isAfter(chargeRuleStartDay)) {
            startDate = plan2ChargeStartDate;
        } else {
            startDate = chargeRuleStartDay;
        }

        // 开始收费时间晚于收费规则费用截止日期，则收费规则已过时
        if (subjectChargeRuleDTO.getChargeEndDay() != null && startDate.isAfter(new DateTime(subjectChargeRuleDTO.getChargeEndDay()))) {
            return null;
        }

        // 收费规则费用截止时间
        DateTime chargeRuleEndDay = subjectChargeRuleDTO.getChargeEndDay() == null ? null : new DateTime(subjectChargeRuleDTO.getChargeEndDay());
        // 收费周期月数
        int monthCountPerPeriod = subjectChargeRuleDTO.getIntervalMonth().intValue();

        // 收费开始时间早于当前时间
        if (startDate.compareTo(plan2ChargeEndDate) <= 0) {
            // 收费规则费用截止时间为空或晚于计划收费截止时间
            if (chargeRuleEndDay == null || chargeRuleEndDay.compareTo(plan2ChargeEndDate) >= 0) {
                PeriodAndDayCount currentPeriodAndDayCount = calcPeriodAndDayCount(startDate, plan2ChargeEndDate, monthCountPerPeriod);
                int currentPeriodCount = currentPeriodAndDayCount.getPeriodCount() + (currentPeriodAndDayCount.getDayCount() > 0 ? 1 : 0);

                int needPayPeriodCount = 0;
                if (PeriodType.CURRENT.equals(subjectChargeRuleDTO.getPeriodType())) {
                    needPayPeriodCount = currentPeriodCount;
                } else if (PeriodType.LAST.equals(subjectChargeRuleDTO.getPeriodType())) {
                    needPayPeriodCount = currentPeriodCount >= 1 ? (currentPeriodCount - 1) : 0;
                } else if (PeriodType.NEXT.equals(subjectChargeRuleDTO.getPeriodType())) {
                    needPayPeriodCount = currentPeriodCount + 1;
                } else {
//                    throw new ("网络异常", "未知的收费周期类型");
                }

                if (needPayPeriodCount <= 0) {
                    return null;
                } else {
                    endDate = startDate.plusMonths(needPayPeriodCount * monthCountPerPeriod).minusDays(1);
                    // 缴至月末
                    // endDate = endDate.dayOfMonth().withMaximumValue();
                    if (chargeRuleEndDay != null && endDate.isAfter(chargeRuleEndDay)) {
                        endDate = chargeRuleEndDay;
                    }
                }
            } else {
                if (PeriodType.LAST.equals(subjectChargeRuleDTO.getPeriodType())) {
                    // 收费规则收费截止时间早于当前时间，需判断当前收上期的情况
                    PeriodAndDayCount periodAndDayCount = calcPeriodAndDayCount(startDate, chargeRuleEndDay, monthCountPerPeriod);
                    int chargeEndDayPeriodCount = periodAndDayCount.getPeriodCount() + (periodAndDayCount.getDayCount() > 0 ? 1 : 0);
                    PeriodAndDayCount currentPeriodAndDayCount = calcPeriodAndDayCount(startDate, plan2ChargeEndDate, monthCountPerPeriod);
                    int currentPeriodCount = currentPeriodAndDayCount.getPeriodCount() + (currentPeriodAndDayCount.getDayCount() > 0 ? 1 : 0);
                    if (currentPeriodCount - chargeEndDayPeriodCount >= 1) {
                        endDate = chargeRuleEndDay;
                    } else {
                        if (chargeEndDayPeriodCount > 1) {
                            endDate = startDate.plusMonths((chargeEndDayPeriodCount - 1) * monthCountPerPeriod).minusDays(1);
                            // 缴至月末
                            // endDate = endDate.dayOfMonth().withMaximumValue();
                        } else {
                            return null;
                        }
                    }
                } else {
                    // 对于当前收当前和当前收下期 收费截止时间为收费规则收费截止时间
                    endDate = chargeRuleEndDay;
                }
            }
        } else {
            // 收费开始时间在当前时间之后
            if (PeriodType.NEXT.equals(subjectChargeRuleDTO.getPeriodType())) {
                if (startDate.minusMonths(monthCountPerPeriod).compareTo(plan2ChargeEndDate) > 0) {
                    return null;
                } else {
                    endDate = startDate.plusMonths(monthCountPerPeriod).minusDays(1);
                    // 缴至月末
                    // endDate = endDate.dayOfMonth().withMaximumValue();
                }
            } else {
                return null;
            }
        }

        if (planCharge && endDate.isAfter(plan2ChargeEndDate)) {
            endDate = plan2ChargeEndDate;
        }

        if (chargeRuleEndDay != null && endDate.isAfter(chargeRuleEndDay)) {
            endDate = chargeRuleEndDay;
        }

        logger.info("startDate:{} endDate:{}", startDate, endDate);

        if (startDate.isAfter(endDate)) {
            return null;
        }

        UnpaidBillItemDTO unpaidBillItemDTO = new UnpaidBillItemDTO();
        unpaidBillItemDTO.setBeginDate(startDate.toDate());
        unpaidBillItemDTO.setEndDate(endDate.toDate());

        return unpaidBillItemDTO;
    }

    private PeriodAndDayCount calcPeriodAndDayCount(DateTime startDate, DateTime endDate, int intervalMonth) {
        // 返回开始和结束时间之间的月份
        int months = Months.monthsBetween(startDate, endDate).getMonths();
        DateTime date = startDate.plusMonths(months);

        // 周期不足时 获取之间的天数
        int days = Days.daysBetween(date, endDate).getDays() + 1;
        // 当前月份的最大天数 + 1
        if (date.dayOfMonth().getMaximumValue() == days) {
            months += 1;
        }

        int periodCount = months / intervalMonth;
        days = Days.daysBetween(startDate.plusMonths(periodCount * intervalMonth), endDate).getDays() + 1;

        return new PeriodAndDayCount(periodCount, days);
    }


    private class PeriodAndDayCount {
        int periodCount;
        int dayCount;

        public PeriodAndDayCount(int periodCount, int dayCount) {
            this.periodCount = periodCount;
            this.dayCount = dayCount;
        }

        public int getPeriodCount() {
            return periodCount;
        }

        public int getDayCount() {
            return dayCount;
        }

        @Override
        public String toString() {
            return "PeriodAndDayCount [periodCount=" + periodCount + ", dayCount=" + dayCount + "]";
        }

    }

    private void calcFee(Subject subject, SubjectChargeRuleRelationDTO subjectChargeRuleDTO, BigDecimal feeDiscount, DateTime startDate, DateTime endDate,
                         UnpaidBillItemDTO unpaidBillItemDTO) {
        // 每个周期的应收金额计算公式
        StringBuilder chargeAmountPerPeriodFormula = new StringBuilder();

        // 计算每个周期的应收金额
        BigDecimal chargeAmountPerPeriod = calcAmountPerPeriod(subject, subjectChargeRuleDTO, unpaidBillItemDTO, chargeAmountPerPeriodFormula);
        if (chargeAmountPerPeriod == null) {
            return;
        }

        // 应收金额计算公式
        StringBuilder feeCalcFormula = new StringBuilder();

        // 计算应收金额
        BigDecimal fee = calcFeeNew(subjectChargeRuleDTO, startDate, endDate, chargeAmountPerPeriodFormula, chargeAmountPerPeriod, feeCalcFormula);

        // 计算折扣
        BigDecimal payAmount = null;
        if (feeDiscount != null) {
            payAmount = fee.multiply(feeDiscount).divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP);
            feeCalcFormula.insert(0, "(");
            feeCalcFormula.append(") x " + decimalToString(feeDiscount));
        } else {
            payAmount = fee;
        }

        unpaidBillItemDTO.setFeeCalcFormula(feeCalcFormula.toString());
        unpaidBillItemDTO.setFee(fee);
        unpaidBillItemDTO.setPayAmount(payAmount);
        unpaidBillItemDTO.setExemptAmount(fee.subtract(payAmount));
        unpaidBillItemDTO.setFeeDiscount(feeDiscount);
    }

    private static String decimalToString(BigDecimal decimal) {
        if (decimal == null) {
            return null;
        }

        String result = decimal.toString();
        if (result.endsWith(".00")) {
            return result.substring(0, result.length() - 3);
        } else {
            return result;
        }
    }


    private BigDecimal calcFeeNew(SubjectChargeRuleRelationDTO subjectChargeRuleDTO, DateTime startDate, DateTime endDate,
                                  StringBuilder chargeAmountPerPeriodFormula, BigDecimal chargeAmountPerPeriod, StringBuilder feeCalcFormula) {
        // 每个收费周期月数
        int monthCountPerPeriod = subjectChargeRuleDTO.getIntervalMonth().intValue();
        BigDecimal fee = BigDecimal.ZERO;

        // 每月费用计算公式
        String chargeAmountPerMonthFormula = null;
        if (monthCountPerPeriod == 1) {
            chargeAmountPerMonthFormula = chargeAmountPerPeriodFormula.toString();
        } else {
            if (chargeAmountPerPeriodFormula.toString().trim().contains("x")) {
                chargeAmountPerMonthFormula = "(" + chargeAmountPerPeriodFormula + ")/" + monthCountPerPeriod;
            } else {
                chargeAmountPerMonthFormula = chargeAmountPerPeriodFormula + "/" + monthCountPerPeriod;
            }
        }

        int startDayOfMonth = startDate.getDayOfMonth();
        int endDayOfMonth = endDate.getDayOfMonth();
        if (startDayOfMonth == startDate.dayOfMonth().withMinimumValue().getDayOfMonth()
                && endDayOfMonth == endDate.dayOfMonth().withMaximumValue().getDayOfMonth()) {
            // 整月数
            int totalMonths = Months.monthsBetween(startDate, endDate).getMonths() + 1;

            if (monthCountPerPeriod == 1) {
                fee = chargeAmountPerPeriod.multiply(new BigDecimal(totalMonths));
                feeCalcFormula.append(chargeAmountPerPeriodFormula + " x " + totalMonths);
            } else {
                int needPayPeriodCount = totalMonths / monthCountPerPeriod;
                int restMonths = totalMonths % monthCountPerPeriod;
                fee = chargeAmountPerPeriod.multiply(new BigDecimal(needPayPeriodCount));
                BigDecimal restMonthsFee = chargeAmountPerPeriod.multiply(new BigDecimal(restMonths)).divide(new BigDecimal(monthCountPerPeriod), 2,
                        BigDecimal.ROUND_HALF_UP);
                fee = fee.add(restMonthsFee);
                feeCalcFormula.append(chargeAmountPerMonthFormula + " x " + totalMonths);
            }
        } else {
            feeCalcFormula.append(chargeAmountPerMonthFormula + " x ");

            boolean justOneMonth = false;
            if (!endDate.isAfter(startDate.dayOfMonth().withMaximumValue())) {
                justOneMonth = true;
            }

            if (!justOneMonth) {
                feeCalcFormula.append("(");
            }

            DateTime startWholeMonthDate = null;
            DateTime endWholeMonthDate = null;
            // 开始时间不是月初
            if (startDayOfMonth != startDate.dayOfMonth().withMinimumValue().getDayOfMonth()) {
                int days = 0;
                if (justOneMonth) {
                    days = Days.daysBetween(startDate, endDate).getDays() + 1;
                } else {
                    days = Days.daysBetween(startDate, startDate.dayOfMonth().withMaximumValue()).getDays() + 1;
                }
                int startMonthTotalDays = startDate.dayOfMonth().withMaximumValue().getDayOfMonth();
                BigDecimal startMonthFee = chargeAmountPerPeriod.multiply(new BigDecimal(days)).divide(
                        new BigDecimal(startMonthTotalDays * monthCountPerPeriod), 2, BigDecimal.ROUND_HALF_UP);

                fee = fee.add(startMonthFee);
                feeCalcFormula.append(days + "/" + startMonthTotalDays);

                if (justOneMonth) {
                    return fee;
                }

                startWholeMonthDate = startDate.dayOfMonth().withMaximumValue().plusDays(1);
            } else {
                startWholeMonthDate = startDate;
            }

            // 结束时间不是月末
            if (endDayOfMonth != endDate.dayOfMonth().withMaximumValue().getDayOfMonth()) {
                int days = Days.daysBetween(endDate.dayOfMonth().withMinimumValue(), endDate.dayOfMonth().withMaximumValue()).getDays() + 1;
                int endMonthTotalDays = endDate.dayOfMonth().withMaximumValue().getDayOfMonth();
                BigDecimal endMonthFee = chargeAmountPerPeriod.multiply(new BigDecimal(days)).divide(new BigDecimal(endMonthTotalDays * monthCountPerPeriod),
                        2, BigDecimal.ROUND_HALF_UP);

                fee = fee.add(endMonthFee);

                if (feeCalcFormula.toString().trim().endsWith("(")) {
                    feeCalcFormula.append("+");
                }
                feeCalcFormula.append(days + "/" + endMonthTotalDays);

                endWholeMonthDate = endDate.dayOfMonth().withMinimumValue().minusDays(1);
            } else {
                endWholeMonthDate = endDate;
            }

            // 包含的整自然月数
            int wholeMonths = Months.monthsBetween(startWholeMonthDate, endWholeMonthDate).getMonths() + 1;
            if (wholeMonths > 0) {
                fee = fee.add(chargeAmountPerPeriod.multiply(new BigDecimal(wholeMonths)).divide(new BigDecimal(monthCountPerPeriod), 2,
                        BigDecimal.ROUND_HALF_UP));

                if (feeCalcFormula.toString().trim().endsWith("(")) {
                    feeCalcFormula.append("+");
                }
                feeCalcFormula.append(wholeMonths);
            }

            feeCalcFormula.append(")");
        }

        logger.info("feeCalcFormula:{}", feeCalcFormula);

        return fee;
    }

    private BigDecimal calcAmountPerPeriod(Subject subject, SubjectChargeRuleRelationDTO subjectChargeRuleDTO, UnpaidBillItemDTO unpaidBillItemDTO,
                                           StringBuilder chargeAmountPerPeriodFormula) {
        // 单价
        BigDecimal unitPrice = null;
        // 数量
        BigDecimal quantity = null;
        // 计算一个周期需要缴纳多少费用
        BigDecimal chargeAmountPerPeriod = BigDecimal.ZERO;
        CalcType calcType = subjectChargeRuleDTO.getCalcType();
        if (CalcType.COUNT.equals(calcType)) {
            Field field = ReflectionUtils.findField(subject.getClass(), subjectChargeRuleDTO.getCountType());
            if (field == null) {
//                logger.error("计算收费金额发生错误：无法获取计数方式,收费标准为：{}", JSONUtil.obj2json(subjectChargeRuleDTO));
                return null;
            }

            field.setAccessible(true);
            Object fieldValue = ReflectionUtils.getField(field, subject);
            if (fieldValue == null) {
//                logger.error("计算收费金额发生错误：无法获取计数数据,收费标准为：{}", JSONUtil.obj2json(subjectChargeRuleDTO));
                return null;
            }

            if (field.getType().equals(BigDecimal.class)) {
                quantity = (BigDecimal) fieldValue;
            } else if (field.getType().equals(Integer.class)) {
                quantity = new BigDecimal(((Integer) fieldValue).intValue());
            } else if (field.getType().equals(Long.class)) {
                quantity = new BigDecimal(((Long) fieldValue).longValue());
            } else {
                quantity = new BigDecimal(fieldValue.toString());
            }
            chargeAmountPerPeriod = subjectChargeRuleDTO.getUnitPrice().multiply(quantity);
            chargeAmountPerPeriodFormula.append(decimalToString(subjectChargeRuleDTO.getUnitPrice()) + " x " + decimalToString(quantity));
            unitPrice = subjectChargeRuleDTO.getUnitPrice();
        } else if (CalcType.FIXED.equals(calcType)) {
            chargeAmountPerPeriod = subjectChargeRuleDTO.getFixedAmount();
            chargeAmountPerPeriodFormula.append(subjectChargeRuleDTO.getFixedAmount());
            unitPrice = subjectChargeRuleDTO.getFixedAmount();
        } else if (CalcType.INPUT_FOR_EVERYONE.equals(calcType)) {
            if (subjectChargeRuleDTO.getInputAmount() == null) {
//                logger.error("计算收费金额发生错误：收费标准计费方式未单独输入，但是在配置收费规则时未指定金额,收费标准为：{}", JSONUtil.obj2json(subjectChargeRuleDTO));
                return null;
            }

            chargeAmountPerPeriod = subjectChargeRuleDTO.getInputAmount();
            chargeAmountPerPeriodFormula.append(subjectChargeRuleDTO.getInputAmount());
            unitPrice = subjectChargeRuleDTO.getInputAmount();
        } else {
//            logger.error("计算收费金额发生错误：收费标准计费方式未单独输入，但是在配置收费规则时未指定金额,收费标准为：{}", JSONUtil.obj2json(subjectChargeRuleDTO));
            return null;
        }

        if (BooleanUtils.isTrue(subjectChargeRuleDTO.getCalcPerMonth())) {
            if (subjectChargeRuleDTO.getIntervalMonth().intValue() > 1) {
                chargeAmountPerPeriod = chargeAmountPerPeriod.multiply(new BigDecimal(subjectChargeRuleDTO.getIntervalMonth()));
                chargeAmountPerPeriodFormula.append(" x " + subjectChargeRuleDTO.getIntervalMonth());
            }
        }

        unpaidBillItemDTO.setUnitPrice(unitPrice);
        unpaidBillItemDTO.setQuantity(quantity);

        return chargeAmountPerPeriod;
    }
}
