package tk.tommy.springboot.service.rd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.tommy.mybatis.dao.PyCustMapper;
import tk.tommy.mybatis.model.PyCust;
@Service
public class RdCustService {

	@Autowired
	private PyCustMapper pyCustMapper;

	public List<PyCust> selectAll() {
		List<PyCust> pyCusts = pyCustMapper.selectAll();
		return pyCusts;
	}

	@Transactional
	public void syncPyCusts(List<PyCust> pyCusts) {
		pyCustMapper.deleteAll();
		for (PyCust pyCust : pyCusts) {
			pyCustMapper.insert(pyCust);
		}

	}
}
