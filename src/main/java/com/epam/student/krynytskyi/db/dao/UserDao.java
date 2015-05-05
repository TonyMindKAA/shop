package com.epam.student.krynytskyi.db.dao;

import java.util.List;

import com.epam.student.krynytskyi.entity.User;

public interface UserDao {
	List<User> getAll();

	boolean add(User user);

	User getByEmail(String email);
}
