package com.tusofia.myapp.model;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "journal")
public class FishJournal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User users;


    @ManyToOne(targetEntity = Fish.class)
    @JoinColumn(name = "fish_id", referencedColumnName = "id")
    private Fish fishes;


    @ManyToOne(targetEntity = Water.class)
    @JoinColumn(name = "water_id", referencedColumnName = "id")
    private Water waters;


    @ManyToOne(targetEntity = Bait.class)
    @JoinColumn(name = "bait_id", referencedColumnName = "id")
    private Bait baits;


    private double size;


    private double weight;


    Date date;

    String info;
    String path;

    boolean shared;


    boolean isEligibleForTourney;

    boolean isModerated;

    public boolean isModerated() {
        return isModerated;
    }

    public void setModerated(boolean isModerated) {
        this.isModerated = isModerated;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isEligibleForTourney() {
        return isEligibleForTourney;
    }

    public void setEligibleForTourney(boolean isEligibleForTourney) {
        this.isEligibleForTourney = isEligibleForTourney;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
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

    public Fish getFishes() {
        return fishes;
    }

    public void setFishes(Fish fishes) {
        this.fishes = fishes;
    }

    public Water getWaters() {
        return waters;
    }

    public void setWaters(Water waters) {
        this.waters = waters;
    }

    public Bait getBaits() {
        return baits;
    }

    public void setBaits(Bait baits) {
        this.baits = baits;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}