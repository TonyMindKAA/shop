package com.epam.student.krynytskyi.service;

import java.util.List;

import com.epam.student.krynytskyi.db.dao.UserLocalDao;
import com.epam.student.krynytskyi.entity.User;

public class StaticUserServiceImpl implements StaticUserService {
	private UserLocalDao userDao;

	public StaticUserServiceImpl(UserLocalDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public boolean add(User user) {
		return userDao.add(user);
	}

	@Override
	public User getByEmail(String email) {
		return userDao.getByEmail(email);
	}

}
