package com.preetamlahre.restfulwebservices.users;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.preetamlahre.restfulwebservices.jpa.PostRepository;
import com.preetamlahre.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserJpaResource {
	private UserDaoService service;
	private UserRepository repository;
	private PostRepository postRepository;
	
	public UserJpaResource(PostRepository postRepository,UserDaoService service,UserRepository repository) {
		this.service=service;
		this.repository=repository;
		this.postRepository=postRepository;
	}
	@GetMapping("/jpa/users")
	public List<User>retrieveAlluser(){
		return repository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id ){
		 Optional<User> user=repository.findById(id);
		 if(user.isEmpty())
			 throw new UserNotFoundException("id:"+id);
		 EntityModel<User>entityModel=EntityModel.of(user.get());
		 
		 WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAlluser());
		 entityModel.add(link.withRel("all-user"));
		 return entityModel;
	}
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id ){
		repository.deleteById(id);
		 
	}
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostForUser(@PathVariable int id ){
		Optional<User> user=repository.findById(id);
		if(user.isEmpty())
			 throw new UserNotFoundException("id:"+id);
		return user.get().getPost();
		
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
		User savedUser = repository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();   
		
		return ResponseEntity.created(location).build();

	}
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		
		post.setUser(user.get());
		
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();   

		return ResponseEntity.created(location).build();
		
	}

}
