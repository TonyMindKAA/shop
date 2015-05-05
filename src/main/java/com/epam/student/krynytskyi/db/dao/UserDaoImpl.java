package com.epam.student.krynytskyi.db.dao;

import java.util.ArrayList;
import java.util.List;

import com.epam.student.krynytskyi.entity.User;
import com.epam.student.krynytskyi.util.ObjectUtil;

public class UserDaoImpl implements UserDao {
	private List<User> users;

	public UserDaoImpl() {
		this.users = new ArrayList<User>();
	}

	public UserDaoImpl(List<User> users) {
		this.users = users;
	}

	@Override
	public List<User> getAll() {
		return users;
	}

	@Override
	public boolean add(User user) {
		ObjectUtil.checkNull(user);
		return users.add(user);
	}

	@Override
	public User getByEmail(String email) {
		ObjectUtil.checkNull(email);
		for (User user : users) {
			if (user.getEmail().equals(email))
				return user;
		}
		return null;
	}

}
