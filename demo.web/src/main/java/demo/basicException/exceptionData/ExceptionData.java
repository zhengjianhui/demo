package demo.basicException.exceptionData;

/**
 * Created by zhengjianhui on 16/10/3.
 * 统一的异常返回信息
 */
public class ExceptionData {

    /**
     * 异常编码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String message;

    public ExceptionData() {
    }

    public ExceptionData(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
