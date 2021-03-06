package com.epam.student.krynytskyi.service.impl;

import com.epam.student.krynytskyi.db.dao.UserDao;
import com.epam.student.krynytskyi.db.dao.mysql.MySqlUserDao;
import com.epam.student.krynytskyi.db.transaction.TransactionManager;
import com.epam.student.krynytskyi.db.transaction.TransactionManagerImpl;
import com.epam.student.krynytskyi.db.transaction.TransactionOperation;
import com.epam.student.krynytskyi.entity.User;
import com.epam.student.krynytskyi.service.UserService;
import com.epam.student.krynytskyi.util.UserBanManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

public class UserServiceImpl implements UserService {
	private static final Logger log = Logger.getLogger(UserServiceImpl.class);
	private TransactionManager transactionManager = new TransactionManagerImpl();
	private UserDao userDao = new MySqlUserDao();
	private UserBanManager userBanManager = new UserBanManager();

	@Override
	public boolean authenticate(final String email, final String password,final HttpServletRequest req)
			throws Exception {
		return (Boolean) transactionManager
				.doInTransaction(new TransactionOperation() {

					@Override
					public Object execute(Connection conn) throws Exception {
						if (email == null || email.length() == 0
								|| password == null)
							return false;
						User userDTO = userDao.getUserByEmail(conn, email);
						if (log.isTraceEnabled())
							log.trace("was found user in db ->" + userDTO);
						if (userDTO == null)
							return false;
						if (!userDTO.getPassword().equals(password)
								|| !userDTO.getEmail().equals(email)) {
							userBanManager.managerAccess(userDTO,req);
							return false;
						}
						else
						return true;
					}
				});
	}

	@Override
	public boolean isExistUser(final String email) throws Exception {
		return (Boolean) transactionManager
				.doInTransaction(new TransactionOperation() {
					@Override
					public Object execute(Connection conn) throws Exception {
						if (userDao.getUserByEmail(conn, email) == null)
							return false;
						return true;
					}
				});
	}

	@Override
	public boolean insertUser(final User newUser) throws Exception {
		if (isExistUser(newUser.getEmail())) {
			String message = "User with given email already exist";
			log.info(message);
			return false;
		}
		return (Boolean) transactionManager
				.doInTransaction(new TransactionOperation() {
					@Override
					public Object execute(Connection conn) throws Exception {
						newUser.setPassword(newUser.getPassword());
						userDao.insertUser(conn, newUser);
						log.info("was created new user ->"
								+ userDao.getUserByEmail(conn,
										newUser.getEmail()));
						return true;
					}
				});
	}

	@Override
	public User getUserByEmail(final String email) throws Exception {
		return (User) transactionManager
				.doInTransaction(new TransactionOperation() {
					@Override
					public Object execute(Connection conn) throws Exception {
						User userDTO = userDao.getUserByEmail(conn, email);
						if (log.isDebugEnabled())
							log.trace("Found user in db ->" + userDTO);
						return userDTO;
					}
				});
	}

	@Override
	public void removeUser(final String email) throws Exception {
		transactionManager.doInTransaction(new TransactionOperation() {
			@Override
			public Object execute(Connection conn) throws Exception {
				userDao.removeUser(conn, email);
				return null;
			}
		});
	}

}
