package demo.basicxception;

/**
 * Created by zhengjianhui on 16/10/7.
 * 自定义异常
 */
public class DemoException extends Exception {

    public DemoException() {
        super();
    }

    public DemoException(String message) {
        super(message);
    }
}
