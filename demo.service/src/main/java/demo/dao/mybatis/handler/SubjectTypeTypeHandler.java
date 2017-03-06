package demo.dao.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import demo.data.datasource.typeHandler.EnumCodeTypeHandler;
import demo.enums.SubjectType;

@MappedTypes(SubjectType.class)
public class SubjectTypeTypeHandler extends EnumCodeTypeHandler<SubjectType> {
    public SubjectTypeTypeHandler() {
        super(SubjectType.class);
    }

}
