package demo.dao.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import demo.data.datasource.typeHandler.EnumCodeTypeHandler;
import demo.enums.PeriodType;


@MappedTypes(PeriodType.class)
public class PeriodTypeTypeHandler extends EnumCodeTypeHandler<PeriodType> {
    public PeriodTypeTypeHandler() {
        super(PeriodType.class);
    }

}
