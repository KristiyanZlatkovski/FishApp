package com.tusofia.myapp.model;

import javax.persistence.*;


@Entity
@Table(name = "fishToWaterProposals")
public class FishToWaterProp {
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

    private String info;

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


}