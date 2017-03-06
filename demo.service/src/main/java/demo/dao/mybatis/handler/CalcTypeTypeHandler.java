package demo.dao.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import demo.data.datasource.typeHandler.EnumCodeTypeHandler;
import demo.enums.CalcType;

@MappedTypes(CalcType.class)
public class CalcTypeTypeHandler extends EnumCodeTypeHandler<CalcType> {
    public CalcTypeTypeHandler() {
        super(CalcType.class);
    }

}
