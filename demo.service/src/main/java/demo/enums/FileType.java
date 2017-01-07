package demo.enums;

import org.springframework.util.Assert;

/**
 * Created by zhengjianhui on 16/10/2.
 *
 * 文件类型枚举
 */
public enum FileType {

    DIRECTORY("0",  "文件夹"),

    FILE("1", "文件"),

    CABINET("2", "文件柜");

    /* key **/
    private String code;
    /* value **/
    private String describe;


    /**
     * 默认私有构造
     * @param code
     * @param describe
     */
    FileType(String code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    /**
     * 获取key
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * 获取 value
     * @return describe
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 根据key 获取对应的 enum 类
     * @param key
     * @return FileType
     */
    public FileType getEnumByKey(String key) {
        Assert.isNull(key, "枚举编码不能为空");

        // values() 获取枚举数组  简写方式 实际为 this.values()
        for (FileType fileType : values()) {
            if(fileType.getCode().equals(key)) return fileType;
        }

        return null;
    }

    /**
     * 根据描述获取枚举值
     * @param describe
     * @return FileType
     */
    public FileType getEnumByDescribe(String describe) {
        Assert.isNull(describe, "枚举编码不能为空");

        // values() 获取枚举数组  简写方式 实际为 this.values()
        for (FileType fileType : values()) {
            if(fileType.getDescribe().equals(describe)) return fileType;
        }

        return null;
    }


}
