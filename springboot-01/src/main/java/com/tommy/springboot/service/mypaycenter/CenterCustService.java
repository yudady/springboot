package com.tommy.springboot.service.mypaycenter;

import com.tommy.mybatis.dao.PyCustMapper;
import com.tommy.mybatis.model.PyCust;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CenterCustService {


	@Autowired PyCustMapper pyCustMapper;

	@Transactional
	public List<PyCust> selectAll(){
		List<PyCust> pyCusts = pyCustMapper.selectAll();
		return pyCusts;
	}
}
