package demo.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import org.apache.commons.lang3.StringUtils;

import demo.data.datasource.typeHandler.EnumCodeGetter;


/**
 * 状态：0-待售，1-已售，2-保留，3-空置，4-已租
 * @author dengzhi
 *
 */
public enum SpaceStatus implements EnumCodeGetter {
    FOR_SALE("0","待售"),

    SOLD("1", "已售"),

    RETAIN("2", "保留"),

    VACANT("3","空置"),

    RENT("4","已租");

    /**
     * 存到数据库的编码
     */
    private String code;

    /**
     * 描述信息
     */
    private String description;

    private SpaceStatus(String code, String description) {
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
    public static SpaceStatus getByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }

        for (SpaceStatus element : SpaceStatus.values()) {
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
    public static SpaceStatus getByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        for (SpaceStatus element : SpaceStatus.values()) {
            if (element.name().equals(StringUtils.upperCase(name))) {
                return element;
            }
        }

        return null;
    }

    /**
     * 根据名称获取对应的枚举对象
     *
     * @param description
     * @return
     */
    public static SpaceStatus getByDescription(String description) {
        if (StringUtils.isEmpty(description)) {
            return null;
        }

        for (SpaceStatus element : SpaceStatus.values()) {
            if (element.getDescription().equals(description)) {
                return element;
            }
        }

        return null;
    }
}
