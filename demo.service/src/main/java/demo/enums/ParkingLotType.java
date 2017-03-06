package demo.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import org.apache.commons.lang3.StringUtils;

import demo.data.datasource.typeHandler.EnumCodeGetter;

public enum ParkingLotType implements EnumCodeGetter {

    UNDERGROUND("1", "地下车库"),

    GROUND("2", "地上车库");

    /**
     * 存到数据库的编码
     */
    private String code;

    /**
     * 描述信息
     */
    private String description;

    private ParkingLotType(String code, String description) {
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
    @JsonCreator
    public static ParkingLotType getByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }

        for (ParkingLotType element : ParkingLotType.values()) {
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
    public static ParkingLotType getByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        for (ParkingLotType element : ParkingLotType.values()) {
            if (element.name().equals(StringUtils.upperCase(name))) {
                return element;
            }
        }

        return null;
    }

}
