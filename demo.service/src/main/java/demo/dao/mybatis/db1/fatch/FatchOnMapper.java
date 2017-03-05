package demo.dao.mybatis.db1.fatch;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import demo.domain.fatch.FatchOn;

@Repository
public interface FatchOnMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FatchOn record) throws DataAccessException;

    int insertSelective(FatchOn record);

    FatchOn selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FatchOn record);

    int updateByPrimaryKey(FatchOn record);
}