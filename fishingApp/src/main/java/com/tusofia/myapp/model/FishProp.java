package com.tusofia.myapp.model;

import javax.persistence.*;


@Entity
@Table(name = "fishProposals")
public class FishProp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(targetEntity =User.class)
	@JoinColumn(name="user_id", referencedColumnName = "id")
    private User users;
    
    private String name;
    
    private double maxSize;
    
    private double maxWeight;
    
    @Column(columnDefinition = "text")
    private String info;
    
    @Column(columnDefinition = "text")
    private String fishInfo;
    
    private String path;

	public String getFishInfo() {
		return fishInfo;
	}

	public void setFishInfo(String fishInfo) {
		this.fishInfo = fishInfo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(double maxSize) {
		this.maxSize = maxSize;
	}

	public double getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
    
    
    
    
}