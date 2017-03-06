package demo.dao.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import demo.data.datasource.typeHandler.EnumCodeTypeHandler;
import demo.enums.ChargeCategory;


@MappedTypes(ChargeCategory.class)
public class ChargeCategoryTypeHandler extends EnumCodeTypeHandler<ChargeCategory> {
    public ChargeCategoryTypeHandler() {
        super(ChargeCategory.class);
    }

}
