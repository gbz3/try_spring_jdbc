package com.github.gbz3.try_spring_webmvc.app.model;

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

import com.github.gbz3.try_spring_webmvc.app.mapper.MyUserMapper;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	MyUserMapper myUserMapper;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final MyUser account = Optional.ofNullable( myUserMapper.findOne(username) )
				.orElseThrow( () -> new UsernameNotFoundException( "user not found." ) );
		return new MyUserDetails( account, getAuthorities( account ) );
	}

	private Collection<GrantedAuthority> getAuthorities( MyUser account ) {
		return AuthorityUtils.createAuthorityList( "ROLE_USER" );
	}

}
