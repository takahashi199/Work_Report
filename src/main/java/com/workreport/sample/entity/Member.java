package com.workreport.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class Member {

	@Id
	@Column
	private String login_id;

	@Column
	private String password;

	@Column
	private String authority;

	@Column
	private boolean deleted_flag;


	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public boolean isDeleted_flag() {
		return deleted_flag;
	}
	public void setDeleted_flag(boolean deleted_flag) {
		this.deleted_flag = deleted_flag;
	}


}
