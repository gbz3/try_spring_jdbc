package com.github.gbz3.authlib.domain.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AuthUserDetails extends User {

	private final AuthUser account;

	public AuthUserDetails(AuthUser account, Collection<GrantedAuthority> authorities) {
		super( account.getUsername(), account.getPassword(),
				true, true, true, true, authorities );
		this.account = account;
	}

	public AuthUser getAccount() {
		return account;
	}

}
