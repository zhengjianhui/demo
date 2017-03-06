package demo.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import org.springframework.util.Assert;

import demo.data.datasource.typeHandler.EnumCodeGetter;


public enum SubjectType implements EnumCodeGetter {

    HOUSE("1", "房屋"),

    PARKINGSPACE("2", "车位"),

    OUTER_ORG("3", "外单位"),

    STOREROOM("4","储藏室");

    private String code;

    private String description;

    private SubjectType(String code, String description) {
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
    public static SubjectType getEnum(String code) {
        Assert.hasText(code, "转成枚举类型code入参不能为空");

        for (SubjectType targetType : SubjectType.values()) {
            if (code.equals(targetType.getCode())) {
                return targetType;
            }
        }
        return null;
    }
}
