package demo.dao.mybatis.interceptorPlugin.page;

import sun.swing.BakedArrayList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengjianhui on 16/10/5.
 *
 * 分页结果
 */
public class PageResult<T> {

    /* 结果集 **/
    private List<T> result;

    /* 总记录数 **/
    private Integer tatol;

    /* 每页显示条数 **/
    private Integer pageSize;

    public PageResult(List<T> obj, Integer pageSize, Integer tatol) { // Integer tatol,

        this.result = new ArrayList();
        this.result.addAll(obj);
        this.tatol = tatol;
        this.pageSize = pageSize;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public Integer getTatol() {
        return tatol;
    }

    public void setTatol(Integer tatol) {
        this.tatol = tatol;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
