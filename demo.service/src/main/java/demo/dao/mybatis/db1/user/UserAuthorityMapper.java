package demo.dao.mybatis.db1.user;


import demo.domain.user.UserAuthority;

public interface UserAuthorityMapper {
    int insert(UserAuthority record);

    int insertSelective(UserAuthority record);
}