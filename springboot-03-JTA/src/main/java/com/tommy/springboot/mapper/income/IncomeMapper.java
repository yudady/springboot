package com.tommy.springboot.mapper.income;

import com.tommy.springboot.bean.Income;
import org.apache.ibatis.annotations.Insert;

/**
 * @author Freud
 */
public interface IncomeMapper {

    @Insert("INSERT INTO INCOME(userId,amount,operateDate) VALUES(#{userId},#{amount},#{operateDate})")
    public void insert(Income income);

}