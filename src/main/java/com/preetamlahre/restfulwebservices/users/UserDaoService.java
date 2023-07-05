package com.preetamlahre.restfulwebservices.users;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users=new ArrayList<>();
	private static int usersCount = 0;
	static {
		users.add(new User(++usersCount,"Preetam",LocalDate.now().minusYears(30)));
		users.add(new User(++usersCount,"Karan",LocalDate.now().minusYears(29)));
		users.add(new User(++usersCount,"Atul",LocalDate.now().minusYears(28)));
	}
	public List<User> findAll(){
		return users;
	}
	public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId()==id; 
		return users.stream().filter(predicate).findFirst().get();
	}
	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId()==id;
		users.removeIf(predicate);
	}
	public User save(User user) {
		user.setId(++usersCount);
		 users.add(user);
		 return user;
	}
}
