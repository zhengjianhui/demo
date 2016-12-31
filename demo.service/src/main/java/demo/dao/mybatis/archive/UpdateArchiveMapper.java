package demo.dao.mybatis.archive;

import demo.domain.UpdateArchive;

public interface UpdateArchiveMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UpdateArchive record);

    int insertSelective(UpdateArchive record);

    UpdateArchive selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UpdateArchive record);

    int updateByPrimaryKey(UpdateArchive record);
}