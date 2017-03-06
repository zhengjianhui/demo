package demo.dao.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import demo.data.datasource.typeHandler.EnumCodeTypeHandler;
import demo.enums.ParkingLotType;


@MappedTypes(ParkingLotType.class)
public class ParkingLotTypeTypeHandler extends EnumCodeTypeHandler<ParkingLotType> {
    public ParkingLotTypeTypeHandler() {
        super(ParkingLotType.class);
    }

}
