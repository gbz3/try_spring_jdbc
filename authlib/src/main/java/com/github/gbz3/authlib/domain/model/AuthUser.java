package com.github.gbz3.authlib.domain.model;

public class AuthUser {

	private String username;
	private String password;
	private String role;
	private String mail;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( "AuthUser{ " );
		sb.append( "username=" ).append( getUsername() );
		sb.append( ", password=" ).append( getPassword() );
		sb.append( ", role=" ).append( getRole() );
		sb.append( ", mail=" ).append( getMail() );
		sb.append( " }" );
		return sb.toString();
	}

}
