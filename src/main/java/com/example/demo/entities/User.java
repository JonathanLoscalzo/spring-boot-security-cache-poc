package com.example.demo.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.demo.utils.JsonParser;

@Entity
public class User implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public enum UserRole {
	ADMIN, CLIENT
    }
    
    public User() {
	this.Roles = new HashSet<UserRole>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ElementCollection(targetClass=UserRole.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<UserRole> Roles;

    public Set<UserRole> getRoles() {
	return Roles;
    }

    public void setRoles(Set<UserRole> roles) {
	Roles = roles;
    }

    public void addRole(UserRole role) {
	this.Roles.add(role);
    }

    // standard getters and setters
    @Override
    public String toString() {
	return JsonParser.writeValue(this);
    }
}