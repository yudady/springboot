package tk.tommy.mybatis.dao;

import tk.tommy.mybatis.model.PyOrder;

public interface PyOrderMapper {
    int deleteByPrimaryKey(Long orderNo);

    int insert(PyOrder record);

    int insertSelective(PyOrder record);

    PyOrder selectByPrimaryKey(Long orderNo);

    int updateByPrimaryKeySelective(PyOrder record);

    int updateByPrimaryKey(PyOrder record);
}