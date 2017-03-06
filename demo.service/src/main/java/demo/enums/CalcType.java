package demo.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import org.apache.commons.lang3.StringUtils;

import demo.data.datasource.typeHandler.EnumCodeGetter;

public enum CalcType implements EnumCodeGetter {

    COUNT("1", "单价*金额"),

    FIXED("2", "固定金额"),
    
    INPUT_FOR_EVERYONE("3", "单独输入");

    /**
     * 存到数据库的编码
     */
    private String code;

    /**
     * 描述信息
     */
    private String description;

    private CalcType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @JsonValue
    public String getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        return this.code + "＝" + this.description;
    }

    /**
     * 
     * 功能描述:根据编码获取对应的枚举对象 <br>
     * 〈功能详细描述〉
     * 
     * @param code
     * @return 返回对应的枚举类对象，匹配不上则返回null
     */
    public static CalcType getByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }

        for (CalcType element : CalcType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }

        return null;
    }

    /**
     * 根据名称获取对应的枚举对象
     * 
     * @param name 名称
     * @return
     */
    public static CalcType getByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        for (CalcType element : CalcType.values()) {
            if (element.name().equals(StringUtils.upperCase(name))) {
                return element;
            }
        }

        return null;
    }

}
