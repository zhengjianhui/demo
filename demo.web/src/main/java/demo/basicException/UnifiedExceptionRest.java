package demo.basicException;

import demo.basicException.exceptionData.ExceptionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

/**
 * Created by zhengjianhui on 16/10/3.
 * @ControllerAdvice 注解 统一的异常处理
 *
 */
@ControllerAdvice
public class UnifiedExceptionRest {

    private static final Logger logger = LoggerFactory.getLogger(UnifiedExceptionRest.class);
    /**
     *
     *
     * @param ex  异常类
     * @return  exceptionData 自定义返回实体
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)     // 返回的状态码
    @ExceptionHandler(Exception.class)          // 拦截的异常类型
    public ExceptionData handleException(Exception ex) {


        ExceptionData exceptionData = null;

        exceptionData = new ExceptionData(HttpStatus.BAD_REQUEST.value(), ex.getMessage());

        logger.error("exception code:" + exceptionData.getCode() + ",exception message:" + exceptionData.getMessage(), ex);
        logger.info("错误信息: {}", ex);
        System.out.println();
        System.out.println();
        System.out.println();

        return exceptionData;
    }
}
