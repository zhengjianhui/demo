package demo.dao.mybatis.db2.ecommerce;

import org.springframework.stereotype.Repository;

import java.util.List;

import demo.domain.ecommerce.Supplier;

@Repository
public interface SupplierMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    Supplier selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);

    List<Supplier> selectSimpleSupplier();
}
