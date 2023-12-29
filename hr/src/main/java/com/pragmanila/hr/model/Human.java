package com.pragmanila.hr.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Human")
public class Human {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private long id;

	@Column(name = "first_name")
	private String first_name;

	@Column(name = "last_name")
	private String last_name;

	public Human() {

	}

	public Human(String first_name, String last_name){
		this.first_name = first_name;
		this.last_name = last_name;
	}

	public long getId(){
		return this.id;
	}
	public String getFirst_name(){
		return this.first_name;
	}
	public String getLast_name(){
		return this.last_name;
	}

	public void setFirst_name(String first_name){
		this.first_name = first_name;
	}

	public void setLast_name(String last_name){
		this.last_name = last_name;
	}

	@Override
	public String toString(){
		return "Human [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + "]";
	}
}
