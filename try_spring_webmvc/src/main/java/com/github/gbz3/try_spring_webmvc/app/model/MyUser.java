package com.github.gbz3.try_spring_webmvc.app.model;

public class MyUser {

	private String username;
	private String password;
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
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( "MyUser{ " );
		sb.append( "username=" ).append( getUsername() );
		sb.append( ", password=" ).append( getPassword() );
		sb.append( ", mail=" ).append( getMail() );
		sb.append( " }" );
		return sb.toString();
	}

}
