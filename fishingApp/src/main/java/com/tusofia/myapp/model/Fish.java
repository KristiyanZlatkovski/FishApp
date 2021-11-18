package com.tusofia.myapp.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.Set;

@Entity
@Table(name = "fish")
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "fishes")
    private Set<Water> waters;

    private double maxSize;

    private double maxWeight;

    @Column(columnDefinition = "text")
    private String info;

    private String path;


    @ManyToMany
    private Set<Bait> baits;


    public Set<Bait> getBaits() {
        return baits;
    }

    public void setBaits(Set<Bait> baits) {
        this.baits = baits;
    }

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

    public Set<Water> getWaters() {
        return waters;
    }

    public void setWaters(Set<Water> waters) {
        this.waters = waters;
    }

    public double getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(double maxSize) {
        this.maxSize = maxSize;
    }

    public double getWeight() {
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

    public void addBait(Bait bait) {
        this.baits.add(bait);
        bait.getFishes().add(this);
    }

    public void removeBait(Bait bait) {
        this.baits.remove(bait);
        bait.getFishes().remove(this);
    }


}