package tk.tommy.springboot.service.mypaycenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.tommy.mybatis.dao.PyCustMapper;
import tk.tommy.mybatis.model.PyCust;
@Service
public class CenterCustService {

	@Autowired
	PyCustMapper pyCustMapper;

	@Transactional
	public List<PyCust> selectAll() {
		List<PyCust> pyCusts = pyCustMapper.selectAll();
		return pyCusts;
	}
}
