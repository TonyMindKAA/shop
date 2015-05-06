package com.epam.student.krynytskyi.service;

import java.util.List;

import com.epam.student.krynytskyi.entity.User;

public interface StaticUserService {
	List<User> getAll();

	boolean add(User user);

	User getByEmail(String email);
}