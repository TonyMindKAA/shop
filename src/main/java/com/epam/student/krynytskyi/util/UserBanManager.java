package com.epam.student.krynytskyi.util;


import com.epam.student.krynytskyi.db.dao.UserDao;
import com.epam.student.krynytskyi.db.dao.mysql.MySqlUserDao;
import com.epam.student.krynytskyi.db.transaction.TransactionManager;
import com.epam.student.krynytskyi.db.transaction.TransactionManagerImpl;
import com.epam.student.krynytskyi.db.transaction.TransactionOperation;
import com.epam.student.krynytskyi.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.Date;

public class UserBanManager {
    private static final Logger log = Logger.getLogger(UserBanManager.class);
    private UserDao userDao = new MySqlUserDao();
    private TransactionManager transactionManager = new TransactionManagerImpl();

    public void managerAccess(User user, HttpServletRequest req) throws Exception {
        if (!isBan(user)) {
            long banEndTime = user.getDateBan().getTime() + user.getTimeBan();
            long timeDifference = new Date().getTime() - banEndTime;
            if (timeDifference >= 0) {
                unban(user);
            }
        }
        Integer errorEntry = (Integer) req.getServletContext().getAttribute("errorEntry");
        Integer currentErrorEntry = getCurrentErrorEntry(req);
        if (currentErrorEntry >= errorEntry) {
            req.getServletContext().setAttribute("currentErrorEntry", new Integer("0"));
            ban(user, req);
        }
        req.getServletContext().setAttribute("currentErrorEntry", ++currentErrorEntry);
    }

    private Integer getCurrentErrorEntry(HttpServletRequest req) {
        Integer currentErrorEntry = (Integer) req.getServletContext().getAttribute("currentErrorEntry");
        if (currentErrorEntry == null) {
            currentErrorEntry = new Integer("0");
            req.getServletContext().setAttribute("currentErrorEntry", currentErrorEntry);
        }
        return currentErrorEntry;
    }

    private void unban(final User user) throws Exception {
        transactionManager.doInTransaction(new TransactionOperation() {
            @Override
            public Object execute(Connection conn) throws Exception {
                userDao.unban(conn, user);
                log.debug("Can not unban user");
                return null;
            }
        });

    }

    private void ban(final User user, HttpServletRequest req) throws Exception {
        Integer timeBan = (Integer) req.getServletContext().getAttribute("banTime");
        user.setTimeBan(timeBan);
        transactionManager.doInTransaction(new TransactionOperation() {
            @Override
            public Object execute(Connection conn) throws Exception {
                userDao.ban(conn, user);
                log.debug("Can not ban user");
                return null;
            }
        });

    }

    private boolean isBan(User user) {
        return user.getIsBan() != 1;
    }
}
