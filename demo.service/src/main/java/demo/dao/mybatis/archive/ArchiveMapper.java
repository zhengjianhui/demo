package demo.dao.mybatis.archive;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import demo.dao.mybatis.interceptorPlugin.page.PageRequest;
import demo.dao.mybatis.interceptorPlugin.page.PageResult;
import demo.domain.Archive;

import java.util.List;
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