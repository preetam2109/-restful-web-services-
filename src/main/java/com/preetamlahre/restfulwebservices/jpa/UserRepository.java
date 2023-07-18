package com.preetamlahre.restfulwebservices.jpa;
import org.springframework.data.jpa.repository.JpaRepository;

import com.preetamlahre.restfulwebservices.users.User;

public interface UserRepository extends JpaRepository<User,Integer>{

}
