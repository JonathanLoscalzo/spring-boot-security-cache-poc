package com.example.demo.events;

public class ExampleEventCreation {
    public Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public ExampleEventCreation(Long id) {
	this.setId(id);
    }
    
}
