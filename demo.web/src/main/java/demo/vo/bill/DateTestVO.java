package demo.vo.bill;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by zhengjianhui on 17/3/31.
 */
public class DateTestVO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date d1;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date d2;

    public Date getD1() {
        return d1;
    }

    public void setD1(Date d1) {
        this.d1 = d1;
    }

    public Date getD2() {
        return d2;
    }

    public void setD2(Date d2) {
        this.d2 = d2;
    }
}
