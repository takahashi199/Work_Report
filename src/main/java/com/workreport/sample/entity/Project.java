package com.workreport.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;

	@Column(name="project_id")
	private String project_id;

	@Column(name="time")
	private String time;

	@Column(name="client")
	private String client;

	@Column(name="member")
	private String member;

	@Column(name="place")
	private String place;

	@Column(name="station")
	private String station;

	@Column(name="detail")
	private String detail;

	@Column(name="create_member")
	private String create_member;

	public int getId() {
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id ;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time){
		this.time = time;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client  = client;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member  = member ;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getCreate_member() {
		return create_member;
	}

	public void setCreate_member(String create_member) {
		this.create_member  = create_member;
	}
}
