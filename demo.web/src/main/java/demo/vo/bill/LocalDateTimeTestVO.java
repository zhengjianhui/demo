package demo.vo.bill;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.Date;

import demo.util.JSONUtil.LocalDateTimeDeserializer;
import demo.util.JSONUtil.LocalDateTimeSerializer;

/**
 * Created by zhengjianhui on 17/3/31.
 */
public class LocalDateTimeTestVO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "uuuu/MM/dd HH:mm:ss")
    private LocalDateTime d1;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "uuuu/MM/dd HH:mm:ss")
    private LocalDateTime d2;

    public LocalDateTime getD1() {
        return d1;
    }

    public void setD1(LocalDateTime d1) {
        this.d1 = d1;
    }

    public LocalDateTime getD2() {
        return d2;
    }

    public void setD2(LocalDateTime d2) {
        this.d2 = d2;
    }
}
