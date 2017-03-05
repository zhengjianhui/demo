package demo.dao.mybatis.db1.user;

import demo.domain.user.Permissions;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionsMapper {
    int deleteByPrimaryKey(@Param("roleNo") String roleNo, @Param("permissionsNo") String permissionsNo);

    int insert(Permissions record);

    int insertSelective(Permissions record);
}