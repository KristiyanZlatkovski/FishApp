package com.tusofia.myapp.model;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "bait")
public class Bait {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
  
   
    @JsonIgnore
    @ManyToMany(mappedBy = "baits")
    private Set<Fish> fishes;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Set<Fish> getFishes() {
		return fishes;
	}

	public void setFishes(Set<Fish> fishes) {
		this.fishes = fishes;
	}
    

    
    
}
