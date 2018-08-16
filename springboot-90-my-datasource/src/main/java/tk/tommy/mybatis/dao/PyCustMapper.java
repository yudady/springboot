package tk.tommy.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import tk.tommy.mybatis.model.PyCust;

public interface PyCustMapper {

	int deleteByPrimaryKey(String id);

	int insert(PyCust record);

	int insertSelective(PyCust record);

	PyCust selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(PyCust record);

	int updateByPrimaryKey(PyCust record);

	List<PyCust> selectAll();

	@Delete("delete from PY_CUST where 1=1")
	void deleteAll();
}