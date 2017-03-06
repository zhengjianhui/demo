package demo.dao.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import demo.data.datasource.typeHandler.EnumCodeTypeHandler;
import demo.enums.SpaceStatus;


@MappedTypes(SpaceStatus.class)
public class SpaceStatusTypeHandler extends EnumCodeTypeHandler<SpaceStatus> {
    public SpaceStatusTypeHandler() {
        super(SpaceStatus.class);
    }

}
