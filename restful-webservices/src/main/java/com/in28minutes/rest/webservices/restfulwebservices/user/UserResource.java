package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		
		if(user == null) {

			UserNotFoundException e = new UserNotFoundException("id-"+id);
			System.out.println("\nBeginning of exception "+e+" end of exception.");
			throw e;
		}
		
		return user;
	}
	
	@DeleteMapping("/users/{id}")
	public User deleteUser(@PathVariable int id) {
		User user = service.deleteUser(id);
		
		if(user==null) {
			UserNotFoundException e = new UserNotFoundException("id-"+id);
			System.out.println("\nBeginning of exception "+e+" end of exception.");
			throw e;			
		}
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object>  createUser(@RequestBody User user) {
		System.out.println("\n**********In createUser()"+ user);
		User savedUser = service.save(user);
		
		
		URI location = ServletUriComponentsBuilder.
				fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
}