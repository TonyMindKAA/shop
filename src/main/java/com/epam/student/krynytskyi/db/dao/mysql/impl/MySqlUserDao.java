package com.epam.student.krynytskyi.db.dao.mysql.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.epam.student.krynytskyi.db.dao.exception.DAOException;
import com.epam.student.krynytskyi.db.dao.mysql.UserDao;
import com.epam.student.krynytskyi.entity.Role;
import com.epam.student.krynytskyi.entity.User;

public class MySqlUserDao implements UserDao {
	private static final Logger log = Logger.getLogger(MySqlUserDao.class);
	private static final String SQL_GET_ALL_USERS = "SELECT *,user.id as userId FROM user, role where role.id = user.idRole;";
	private static final String SQL_GET_USER_BY_EMAIL = "SELECT *,user.id as userId FROM user, role where role.id = user.idRole and email = ?;";
	private static final String SQL_INSERT_NEW_USER = "INSERT INTO user (name,last_name,phone,password,email,img,idRole,id) VALUES (?,?,?,?,?,?,?,?);";
	private static final String SQL_REMOVE_USER_BY_EMAIL = "DELETE FROM user WHERE user.email=?";
	private static final String SQL_UPDATE_USER = "UPDATE user SET user.name=?, user.last_name=?, user.password=?, user.phone=?, user.img=?, user.role=?, user.email=?, user.iduser=? WHERE user.id=?;";

	@Override
	public User getUserByEmail(Connection conn, String email)
			throws DAOException {
		try (PreparedStatement pstmt = conn
				.prepareStatement(SQL_GET_USER_BY_EMAIL)) {
			User user = null;
			ResultSet rs = null;
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next())
				user = extractUserDTO(rs);
			return user;
		} catch (Exception e) {
			String message = "Can not perform query";
			log.error(message, e);
			throw new DAOException(message, e);
		}
	}

	@Override
	public List<User> getAll(Connection conn) throws DAOException {
		try (Statement stmt = conn.createStatement()) {
			List<User> users = new ArrayList<>();
			User user = null;
			ResultSet rs = null;
			rs = stmt.executeQuery(SQL_GET_ALL_USERS);
			while (rs.next()) {
				user = extractUserDTO(rs);
				users.add(user);
			}
			return users;
		} catch (Exception e) {
			String message = "Can not perform query";
			log.error(message, e);
			throw new DAOException(message, e);
		}
	}

	@Override
	public void insertUser(Connection conn, User user) throws DAOException {
		try (PreparedStatement pstmt = conn
				.prepareStatement(SQL_INSERT_NEW_USER)) {
			user.setId(getNewStringId());
			packUserDTO(pstmt, user);
			pstmt.executeUpdate();
		} catch (Exception e) {
			String message = "Cannot perform query";
			log.error(message, e);
			throw new DAOException(message, e);
		}
	}

	private String getNewStringId() {
		return UUID.randomUUID().toString();
	}

	@Override
	public void updateUser(Connection conn, User user) throws DAOException {
		try (PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_USER)) {
			packUserDTO(pstmt, user);
			pstmt.setString(9, user.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			String message = "Can not perform query";
			log.error(message, e);
			throw new DAOException(message, e);
		}
	}

	@Override
	public void removeUser(Connection conn, String email) throws DAOException {
		try (PreparedStatement pstmt = conn
				.prepareStatement(SQL_REMOVE_USER_BY_EMAIL)) {
			pstmt.setString(1, email);
			pstmt.executeUpdate();
		} catch (Exception e) {
			String message = "Can not perform query";
			log.error(message, e);
			throw new DAOException(message, e);
		}
	}
	
	private User extractUserDTO(ResultSet rs) throws SQLException {
		Role role = new Role();
		User newUser = new User();
		newUser.setEmail(rs.getString("email"));
		newUser.setImg(rs.getString("img"));
		newUser.setLastName(rs.getString("last_name"));
		newUser.setName(rs.getString("name"));
		newUser.setPassword(rs.getString("password"));
		newUser.setPhone(rs.getString("phone"));
		newUser.setId(rs.getString("userId"));
		role.setId(rs.getInt("iDrole"));
		role.setRole(rs.getString("role"));
		newUser.setRole(role);
		return newUser;
	}

	private void packUserDTO(PreparedStatement pstmt, User user)
			throws SQLException {
		pstmt.setString(1, user.getName());
		pstmt.setString(2, user.getLastName());
		pstmt.setString(3, user.getPhone());
		pstmt.setString(4, user.getPassword());
		pstmt.setString(5, user.getEmail());
		pstmt.setString(6, user.getImg());
		pstmt.setString(7, Integer.toString(user.getRole().getId()));
		pstmt.setString(8, user.getId());
	}

}
