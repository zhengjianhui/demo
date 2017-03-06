package demo.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import org.springframework.util.Assert;

import demo.data.datasource.typeHandler.EnumCodeGetter;

public enum PeriodType implements EnumCodeGetter {

    CURRENT("1", "当前收当前"),
    
    LAST("2", "当前收上期"),
    
    NEXT("3", "当前收下期"); 

    private String code;

    private String description;

    private PeriodType(String code, String description) {
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
    public static PeriodType getEnum(String code) {
        Assert.hasText(code, "转成枚举类型code入参不能为空");

        for (PeriodType targetType : PeriodType.values()) {
            if (code.equals(targetType.getCode())) {
                return targetType;
            }
        }
        return null;
    }
}
