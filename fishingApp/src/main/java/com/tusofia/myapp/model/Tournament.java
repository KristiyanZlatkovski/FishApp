package com.tusofia.myapp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "tournament")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "varchar(255) default 'Активен'")
    private String status;

    private String hostName;

    private String rewardsInfo;

    Date startingDate;

    Date endDate;

    @ManyToOne(targetEntity = Fish.class)
    @Nullable
    @JoinColumn(name = "fish_id", referencedColumnName = "id")
    private Fish fishes;


    @ManyToOne(targetEntity = Water.class)
    @Nullable
    @JoinColumn(name = "water_id", referencedColumnName = "id")
    private Water waters;


    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private Set<TournamentRewards> rewards = new HashSet<>();


    public Set<TournamentRewards> getRewards() {
        return rewards;
    }


    public void setRewards(Set<TournamentRewards> rewards) {
        this.rewards = rewards;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
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


    public String getHostName() {
        return hostName;
    }


    public void setHostName(String hostName) {
        this.hostName = hostName;
    }


    public String getRewardsInfo() {
        return rewardsInfo;
    }


    public void setRewardsInfo(String rewardsInfo) {
        this.rewardsInfo = rewardsInfo;
    }


    public Date getStartingDate() {
        return startingDate;
    }


    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }


    public Date getEndDate() {
        return endDate;
    }


    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public Optional<Fish> getFishes() {


        return Optional.ofNullable(fishes);
    }


    public void setFishes(Fish fishes) {
        this.fishes = fishes;
    }


    public Optional<Water> getWaters() {


        return Optional.ofNullable(waters);
    }


    public void setWaters(Water waters) {
        this.waters = waters;
    }

    public void addReward(TournamentRewards reward) {
        this.rewards.add(reward);
        reward.setTournament(this);
    }


}
