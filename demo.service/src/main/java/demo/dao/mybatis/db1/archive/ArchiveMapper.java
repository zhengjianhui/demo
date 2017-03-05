package demo.dao.mybatis.db1.archive;

import org.apache.ibatis.annotations.MapKey;

import demo.dao.mybatis.interceptorPlugin.page.PageRequest;
import demo.dao.mybatis.interceptorPlugin.page.PageResult;
import demo.domain.Archive;

import java.util.Map;

public interface ArchiveMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Archive record);

    int insertSelective(Archive record);

    Archive selectByPrimaryKey(Long id);

    PageResult<Archive> selectList(PageRequest page, Archive archive);

    int updateByPrimaryKeySelective(Archive record);

    int updateByPrimaryKey(Archive record);

    @MapKey("parentId")
    Map<Long, Archive> selectByParentId(Long parentId);


}