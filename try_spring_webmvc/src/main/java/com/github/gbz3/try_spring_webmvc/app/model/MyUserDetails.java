package com.github.gbz3.try_spring_webmvc.app.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyUserDetails extends User {

	private final MyUser account;

	public MyUserDetails(MyUser account, Collection<GrantedAuthority> authorities) {
		super( account.getUsername(), account.getPassword(),
				true, true, true, true, authorities );
		this.account = account;
	}

	public MyUser getAccount() {
		return account;
	}

}
