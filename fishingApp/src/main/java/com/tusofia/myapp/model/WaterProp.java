package com.tusofia.myapp.model;

import javax.persistence.*;


@Entity
@Table(name = "waterProposals")
public class WaterProp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(targetEntity =User.class)
	@JoinColumn(name="user_id", referencedColumnName = "id")
    private User users;
    
    private String name;
    
    private double longtitude;

    private double latitude;
    
    private String path;
    
    
    @Column(columnDefinition = "text")
    private String waterInfo;
    
    
    @Column(columnDefinition = "text")
    private String info;

	public String getWaterInfo() {
		return waterInfo;
	}

	public void setWaterInfo(String waterInfo) {
		this.waterInfo = waterInfo;
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

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
    
}
