package tk.yudady.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import tk.yudady.pojo.Address;
import tk.yudady.pojo.College;
import tk.yudady.pojo.User;
@Service
public class UserService {
	public User getUserById(String userId) {
		Predicate<User> userPredicate = u-> u.getUserId().equals(userId);
		User obj = list.stream().filter(userPredicate).findFirst().get();
		return obj;
	}	
	public List<User> getAllUsers() { 
		return list;
	}		
	private List<User> list = new ArrayList<>();
	{
		Address address = new Address("A-32", "Varanasi", "India");
		College college = new College("UP College", "Varanasi");
		User user = new User("mohan", "m123", 20, 988745 ,"mohan@gmail.com", "Mohan", college, address);
		list.add(user);
		address = new Address("C-50", "Allahabad", "India");
		college = new College("Allahabad University", "Allahabad");
		user = new User("krishna", "k123", 25, 988345, "krishna@gmail.com", "Krishna", college, address);
		list.add(user);		
		address = new Address("D-40", "Delhi", "India");
		college = new College("JNU", "Delhi");
		user = new User("mahesh", "mh123", 30, 988765, "mahesh@gmail.com", "Mahesh", college, address);
		list.add(user);		
	}
}
