package com.example.demo.web;

import java.util.ArrayList;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Example;
import com.example.demo.repository.ExampleRepository;
import com.github.javafaker.Faker;

@RestController
@RequestMapping("/api/private")
public class PrivateController {
    @Autowired
    private ExampleRepository examples;
    
    @Autowired
    private HttpServletRequest context;

    @GetMapping
    public String getMessage() {
	return "Hello from private API controller";
    }

    @GetMapping("/admin")
    @RolesAllowed({ "ROLE_ADMIN" })
    public String onlyAdminAccepted() {
	return "Hello from private API controller";
    }

    @GetMapping("/createExampleFakeData")
    public String createExampleFakeData() {
	Faker faker = new Faker();
	ArrayList<Example> entities = new ArrayList<Example>();

	for (int i = 0; i < 10; i++) {
	    Example e = new Example();
	    e.setName(faker.name().firstName());
	    entities.add(e);
	}

	examples.saveAll(entities);
	return "Hello from private API controller";
    }
}