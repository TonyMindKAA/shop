package com.epam.student.krynytskyi.db.dao;

import com.epam.student.krynytskyi.entity.User;

import java.util.List;

public interface UserLocalDao {
	List<User> getAll();

	boolean add(User user);

	User getByEmail(String email);
}
