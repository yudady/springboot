package tk.tommy.springboot.service.mypay;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OrderLogService {

	public List<Object> getPyMyPayOrderLog(String custName) {

		System.out.println("OrderLogService :　" + custName);
		return null;
	};
}
