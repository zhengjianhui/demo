package demo.contorller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import demo.dao.mybatis.db2.ecommerce.SupplierMapper;
import demo.domain.ecommerce.Supplier;
import demo.vo.SimpleSupplierVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by zhengjianhui on 17/3/5.
 */
@Api("那么大测试")
@RequestMapping
@RestController
public class DB2Controller {

    @Autowired
    private SupplierMapper supplierMapper;


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

}
