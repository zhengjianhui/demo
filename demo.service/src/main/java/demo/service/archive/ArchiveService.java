package demo.service.archive;

import demo.dao.mybatis.interceptorPlugin.page.PageRequest;
import demo.dao.mybatis.interceptorPlugin.page.PageResult;
import demo.domain.Archive;

import java.util.List;

/**
 * Created by zhengjianhui on 16/10/2.
 */
public interface ArchiveService {

    public void add();

    public PageResult<Archive> queryList(PageRequest page);


}
