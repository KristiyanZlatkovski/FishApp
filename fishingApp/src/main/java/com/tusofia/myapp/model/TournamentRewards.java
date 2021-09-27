package com.tusofia.myapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tournamentRewards")
public class TournamentRewards{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private int placing;
	
	private String name;
	
	private double monetaryValue;
	
	
	@ManyToOne(targetEntity =Tournament.class)
	@JoinColumn(name="tournament_id", referencedColumnName = "id")
	@JsonIgnore
    private Tournament tournament;

	@ManyToOne(targetEntity =User.class)
	@JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;
	
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getPlacing() {
		return placing;
	}


	public void setPlacing(int placing) {
		this.placing = placing;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getMonetaryValue() {
		return monetaryValue;
	}


	public void setMonetaryValue(double monetaryValue) {
		this.monetaryValue = monetaryValue;
	}


	public Tournament getTournament() {
		return tournament;
	}


	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}
	
}
