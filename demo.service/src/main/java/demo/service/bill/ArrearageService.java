package demo.service.bill;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import demo.dto.bill.UnpaidBillDTO;

/**
 * Created by zhengjianhui on 17/3/6.
 */
public interface ArrearageService {

    Map<Long, List<UnpaidBillDTO>> getParkingLotAllUnpaidBill(Long blockId, Long parkingLotId, Long chargeItemId, boolean needCalcAmount,
                                                              LocalDateTime chargeEndDay);
}
