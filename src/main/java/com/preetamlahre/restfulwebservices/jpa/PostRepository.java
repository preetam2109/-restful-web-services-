package com.preetamlahre.restfulwebservices.jpa;
import org.springframework.data.jpa.repository.JpaRepository;

import com.preetamlahre.restfulwebservices.users.Post;

public interface PostRepository extends JpaRepository<Post,Integer>{

}
