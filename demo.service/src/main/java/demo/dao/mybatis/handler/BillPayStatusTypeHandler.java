package demo.dao.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import demo.data.datasource.typeHandler.EnumCodeTypeHandler;
import demo.enums.BillPayStatus;


@MappedTypes(BillPayStatus.class)
public class BillPayStatusTypeHandler extends EnumCodeTypeHandler<BillPayStatus> {
    public BillPayStatusTypeHandler() {
        super(BillPayStatus.class);
    }

}
