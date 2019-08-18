package com.github.gbz3.authlib.domain.mapper;

import com.github.gbz3.authlib.domain.model.AuthUser;

public interface AuthUserMapper {

	AuthUser findOne( String username );

}
