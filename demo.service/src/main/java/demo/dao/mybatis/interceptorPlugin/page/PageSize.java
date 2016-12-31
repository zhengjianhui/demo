package demo.dao.mybatis.interceptorPlugin.page;

import org.omg.PortableInterceptor.Interceptor;

/**
 * Created by zhengjianhui on 16/10/5.
 *
 * 分页计算
 */
public class PageSize {

    private String offset;

    private String limit;

    public PageSize(PageRequest pageRequest) {
        pageCalculate(pageRequest);
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    private void pageCalculate(PageRequest pageRequest) {
        this.offset = String.valueOf(pageRequest.getNowPage() * pageRequest.getPageSize());
        this.limit = String.valueOf(offset + pageRequest.getPageSize());
    }
}
