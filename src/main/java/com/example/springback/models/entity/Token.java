package com.example.springback.models.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tokens")
public class Token implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_token")
	private Integer idToken;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_users")
	private Usuario idUsers;
	
	@Column(name = "token", unique = true)
	private String token;

	public Usuario getIdUsers() {
		return idUsers;
	}

	public void setIdUsers(Usuario object) {
		this.idUsers = object;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
