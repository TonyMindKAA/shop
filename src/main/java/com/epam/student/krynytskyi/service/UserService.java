package com.epam.student.krynytskyi.service;

import com.epam.student.krynytskyi.entity.User;

public interface UserService {
	public boolean authentificate(String email, String password) throws Exception;

	public boolean isExistUser(String email) throws Exception;

	public boolean insertUser(User newUser) throws Exception;

	public User getUserByEmail(String email) throws Exception;

	public void removeUser(String email) throws Exception;
}
