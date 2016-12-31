package demo.dao.mybatis.user;


import demo.domain.user.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(String no);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String no);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}