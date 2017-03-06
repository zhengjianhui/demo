package demo.dao.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import demo.data.datasource.typeHandler.EnumCodeTypeHandler;
import demo.enums.BillCheckStatus;


@MappedTypes(BillCheckStatus.class)
public class BillCheckStatusTypeHandler extends EnumCodeTypeHandler<BillCheckStatus> {
    public BillCheckStatusTypeHandler() {
        super(BillCheckStatus.class);
    }

}
