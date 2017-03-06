package demo.data.datasource.typeHandler;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 枚举类描述信息
 *
 * @author 张功锋
 *
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Description {

    /**
     * 
     * 功能描述:描述信息 <br>
     *
     * @return
     */
    String value() default "";

}
