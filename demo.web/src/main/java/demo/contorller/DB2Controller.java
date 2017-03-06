package demo.contorller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import demo.dao.mybatis.db2.ecommerce.SupplierMapper;
import demo.domain.ecommerce.Supplier;
import demo.dto.bill.UnpaidBillDTO;
import demo.service.bill.ArrearageService;
import demo.vo.SimpleSupplierVO;
import demo.vo.bill.UnpaidBillVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Created by zhengjianhui on 17/3/5.
 */
@Api("那么大测试")
@RequestMapping
@RestController
public class DB2Controller {

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private ArrearageService arrearageService;


    @ApiOperation("2号数据源测试")
    @RequestMapping(value = "db2/supplier/queryAll", method = RequestMethod.GET)
    public List<SimpleSupplierVO> queryAll() {

        return supplierMapper.selectSimpleSupplier().stream().map(x -> getVO(x)).collect(Collectors.toList());

    }

    private SimpleSupplierVO getVO(Supplier supplier) {
        SimpleSupplierVO v = new SimpleSupplierVO();
        BeanUtils.copyProperties(supplier, v);
        return v;
    }


    @ApiOperation(value = "获取指定停车场下的所有车位的应缴账单信息")
    @RequestMapping(value = "/parkingSpaceUnpaidBills/queryByParkingLotId", method = RequestMethod.GET)
    public Map<Long, List<UnpaidBillVO>> getParkingLotAllUnpaidBill(
            @ApiParam(name = "parkingLotId", value = "停车场id", required = true) @RequestParam(value = "parkingLotId", required = true) Long parkingLotId,
            @ApiParam(name = "chargeItemId", value = "收费项id，如果参数为空，则查所有收费项的应缴账单", required = false) @RequestParam(value = "chargeItemId", required = false) Long chargeItemId,
            @ApiParam(name = "needCalcAmount", value = "是否要计算欠费金额", required = false) @RequestParam(value = "needCalcAmount", required = false) boolean needCalcAmount,
            @ApiParam(name = "chargeEndDay", value = "缴费截至时间，如果为空，则以当前时间作为截至时间", required = false) @RequestParam(value = "chargeEndDay", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date chargeEndDay) {
//        Long blockId = ShiroSecurityUtils.getUser().getId()

        Long blockId = 1L;

        Map<Long, List<UnpaidBillDTO>> unpaidBills = arrearageService.getParkingLotAllUnpaidBill(blockId, parkingLotId, chargeItemId, needCalcAmount,
                chargeEndDay);
        Map<Long, List<UnpaidBillVO>> result = new HashMap<>(unpaidBills.size());
        for (Map.Entry<Long, List<UnpaidBillDTO>> entry : unpaidBills.entrySet()) {
            List<UnpaidBillVO> list = new ArrayList<>(entry.getValue().size());
            for (UnpaidBillDTO unpaidBillDTO : entry.getValue()) {
                UnpaidBillVO unpaidBillVO = new UnpaidBillVO();
                BeanUtils.copyProperties(unpaidBillDTO, unpaidBillVO);
                list.add(unpaidBillVO);
            }
            result.put(entry.getKey(), list);
        }
        return result;
    }

}
