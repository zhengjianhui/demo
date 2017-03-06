package demo.data.datasource.interceptorPlugin.page;

import org.springframework.data.domain.Pageable;

/**
 * Created by zhengjianhui on 16/10/5.
 *
 * 分页计算
 */
public class PageSize {

    private String offset;

    private String limit;

    public PageSize(Pageable pageRequest) {
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

    private void pageCalculate(Pageable pageRequest) {
        this.offset = String.valueOf(pageRequest.getPageNumber() * pageRequest.getPageSize());
        this.limit = String.valueOf(pageRequest.getPageSize());
    }
}
