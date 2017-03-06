package demo.dao.mybatis.db2.bill;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import demo.domain.bill.ChargeItem;


@Repository
public interface ChargeItemMapper {
//	int deleteByPrimaryKey(Long id);
//
//	int insert(ChargeItem record);
//
//	ChargeItem selectByPrimaryKey(Long id);
//
//	int updateByPrimaryKeySelective(ChargeItem record);
//
//	int updateByPrimaryKey(ChargeItem record);

	List<ChargeItem> selectByBlockId(Long blockId);

//	List<ChargeItem> selectBySubjectTypeAndCategory(Map<String, Object> args);
//
//	List<ChargeItem> selectByIds(List<Long> chargeItemIdList);
//
//	ChargeItem selectByBlockIdAndName(@Param("blockId") Long blockId, @Param("name") String name);
//
//    List<ChargeItem> selectByCategory(Map<String, Object> args);
}