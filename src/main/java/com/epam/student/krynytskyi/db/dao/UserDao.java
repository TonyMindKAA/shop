package com.epam.student.krynytskyi.db.dao;

import com.epam.student.krynytskyi.db.dao.exception.DAOException;
import com.epam.student.krynytskyi.entity.User;

import java.sql.Connection;
import java.util.List;

public interface UserDao {
	public User getUserByEmail(Connection conn, String email) throws DAOException;

	public List<User> getAll(Connection conn) throws DAOException;

	public void insertUser(Connection conn, User user) throws DAOException;

	public void updateUser(Connection conn, User user) throws DAOException;

	public void removeUser(Connection conn, String email) throws DAOException;

	void unban(Connection conn,User user) throws DAOException;

	void ban(Connection conn,User user) throws DAOException;
}
