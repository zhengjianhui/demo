package demo.dao.mybatis.interceptorPlugin.page;

/**
 * Created by zhengjianhui on 16/10/4.
 */
public class PageRequest {

    /* 当前页数 **/
    private Integer nowPage;

    /* 每页显示条数 **/
    private Integer pageSize;

    /* 是否排序 **/
    private String sqrt;




    public Integer getNowPage() {
        return nowPage;
    }

    public void setNowPage(Integer nowPage) {
        this.nowPage = nowPage;
    }

    public String getSqrt() {
        return sqrt;
    }

    public void setSqrt(String sqrt) {
        this.sqrt = sqrt;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
