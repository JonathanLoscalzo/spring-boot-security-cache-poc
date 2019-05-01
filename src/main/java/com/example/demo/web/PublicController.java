package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @Autowired
    private UserRepository users;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping
    public String getMessage() {
        return "Hello from public API controller";
    }
    
    //https://medium.com/@MatthewFTech/spring-boot-cache-with-redis-56026f7da83a
    @CacheEvict(value="users")
    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
	//VALIDATE USER CREATION
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addRole(User.UserRole.CLIENT);
        users.save(user);
    }
    
    @Cacheable(value="users")
    @GetMapping("/users")
    public List<User> getUsers(){
	return users.findAll();
    }
}