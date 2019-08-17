package com.github.gbz3.try_spring_webmvc.app.mapper;

import java.util.List;

import com.github.gbz3.try_spring_webmvc.app.model.MyUser;

public interface MyUserMapper {

	MyUser findOne(String username);
	long count();
	List<MyUser> findAll();

}
