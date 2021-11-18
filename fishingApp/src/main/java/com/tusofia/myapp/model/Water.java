package com.tusofia.myapp.model;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "water")
public class Water {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double longtitude;

    private double latitude;

    private String path;

    @Column(columnDefinition = "text")
    private String info;


    @JsonIgnore
    @ManyToMany
    private Set<Fish> fishes;


    public String getInfo() {
        return info;
    }


    public void setInfo(String info) {
        this.info = info;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
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


    public Set<Fish> getFishes() {
        return fishes;
    }


    public void setFishes(Set<Fish> fishes) {
        this.fishes = fishes;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void addFish(Fish fish) {
        this.fishes.add(fish);
        fish.getWaters().add(this);
    }

    public void removeFish(Fish fish) {
        this.fishes.remove(fish);
        fish.getWaters().remove(this);
    }


}
