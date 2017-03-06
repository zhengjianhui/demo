package demo.data.datasource.typeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 枚举类类型处理器，用于mybatis存储以及查询数据与枚举类型的转换
 *
 * TypeHandler mybatis 自带的的类型  可以用于实现自定义类型转换器
 *
 * @author ZGF
 * 
 */
public abstract class EnumCodeTypeHandler<E extends Enum<E> & EnumCodeGetter> extends BaseTypeHandler<E> {

    private final E[] enums;

    public EnumCodeTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.enums = type.getEnumConstants();
        if (this.enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }
    }

    /**
     * 根据列名，获取可以为空的结果
     * @param rs
     * @param name
     * @return
     * @throws SQLException
     */
    @Override
    public E getNullableResult(ResultSet rs, String name) throws SQLException {
        return convert(rs.getString(name));
    }

    /**
     * 根据列索引，获取可以为空的结果
     * @param rs
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public E getNullableResult(ResultSet rs, int i) throws SQLException {
        return convert(rs.getString(i));
    }

    @Override
    public E getNullableResult(CallableStatement cs, int i) throws SQLException {
        return convert(cs.getString(i));
    }

    /**
     * 设置非空参数
     * @param ps
     * @param i
     * @param enumObj
     * @param type
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E enumObj, JdbcType type) throws SQLException {
        ps.setString(i, enumObj.getCode());
    }

    private E convert(String status) {
        for (E em : enums) {
            if (em.getCode().equals(status)) {
                return em;
            }
        }
        return null;
    }
}
