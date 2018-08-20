package tk.tommy.springboot.service.rd;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.tommy.springboot.dao.rd.RdRepository;
@Service
public class RdService {

	@Autowired
	RdRepository rdRepository;

	public List<Map<String, Object>> queryForListPyNotYetNotifyOrder() {
		return rdRepository.queryForListPyNotYetNotifyOrder();
	}

	public List<Map<String, Object>> queryForListPyNotifyFailOrder() {
		return rdRepository.queryForListPyNotifyFailOrder();
	}

	public List<Map<String, Object>> queryForListPyOrder() {
		return rdRepository.queryForListPyOrder();
	}

	@Transactional
	public int rdFail() {
		rdRepository.rdOk();
		return rdRepository.rdFail();
	}

	@Transactional
	public Integer rdOk() {
		return rdRepository.rdOk();
	}
}
