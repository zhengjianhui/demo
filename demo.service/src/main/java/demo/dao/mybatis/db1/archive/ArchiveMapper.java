package demo.dao.mybatis.db1.archive;

import org.apache.ibatis.annotations.MapKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

import demo.domain.Archive;

public interface ArchiveMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Archive record);

    int insertSelective(Archive record);

    Archive selectByPrimaryKey(Long id);

    Page<Archive> selectList(Pageable page, Archive archive);

    int updateByPrimaryKeySelective(Archive record);

    int updateByPrimaryKey(Archive record);

    @MapKey("parentId")
    Map<Long, Archive> selectByParentId(Long parentId);


}