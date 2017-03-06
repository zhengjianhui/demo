package demo.dao.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import demo.data.datasource.typeHandler.EnumCodeTypeHandler;
import demo.enums.PropertyRightType;


@MappedTypes(PropertyRightType.class)
public class ParkingSpaceTypeTypeHandler extends EnumCodeTypeHandler<PropertyRightType> {
    public ParkingSpaceTypeTypeHandler() {
        super(PropertyRightType.class);
    }

}
