package demo.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import org.springframework.util.Assert;

import demo.data.datasource.typeHandler.EnumCodeGetter;

/**
 * 消息接受类型枚举类<TargetType>
 *
 * @author zgf
 *
 */
public enum ChargeCategory implements EnumCodeGetter {

    NORMAL("1", "常规收费"),

    SHARE("2", "公摊收费"),

    TEMPORARY("3", "临时收费"),

    DEPOSIT("4", "押金收费"),

    PERPAID("5","预交收费");

    private String code;

    private String description;

    private ChargeCategory(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "(" + this.code + "," + this.description + ")";
    }

    /**
     * 功能描述: <br>
     * 通过Code获取到枚举对象
     *
     * @param code
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static ChargeCategory getEnum(String code) {
        Assert.hasText(code, "转成枚举类型code入参不能为空");

        for (ChargeCategory targetType : ChargeCategory.values()) {
            if (code.equals(targetType.getCode())) {
                return targetType;
            }
        }
        return null;
    }
}
