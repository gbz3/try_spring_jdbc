package com.github.gbz3.authlib.domain.model;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.gbz3.authlib.domain.mapper.AuthUserMapper;

@Service
public class AuthUserDetailsService implements UserDetailsService {

	@Autowired
	AuthUserMapper authUserMapper;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final AuthUser account = Optional.ofNullable( authUserMapper.findOne(username) )
				.orElseThrow( () -> new UsernameNotFoundException( "user not found." ) );
		return new AuthUserDetails( account, getAuthorities( account ) );
	}

	private Collection<GrantedAuthority> getAuthorities( AuthUser account ) {
		return AuthorityUtils.createAuthorityList( account.getRole() );
	}

}
