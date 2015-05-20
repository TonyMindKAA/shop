package com.epam.student.krynytskyi.service.impl;

import com.epam.student.krynytskyi.db.dao.OrderDao;
import com.epam.student.krynytskyi.db.dao.mysql.MySqlOrderDao;
import com.epam.student.krynytskyi.db.transaction.TransactionManager;
import com.epam.student.krynytskyi.db.transaction.TransactionManagerImpl;
import com.epam.student.krynytskyi.db.transaction.TransactionOperation;
import com.epam.student.krynytskyi.entity.Order;
import com.epam.student.krynytskyi.service.OrderService;

import java.sql.Connection;

public class OrderServiceImpl implements OrderService{
    private TransactionManager transactionManager = new TransactionManagerImpl();
    private OrderDao orderDao = new MySqlOrderDao();

    @Override
    public Order addOrder(final Order order) throws Exception {
        Order orderWithId = addOrderWithId(order);
        return orderWithId;
    }

    private Order addOrderWithId(final Order order) throws Exception {
        return (Order) transactionManager.doInTransaction(new TransactionOperation() {
            @Override
            public Object execute(Connection conn) throws Exception {
                return orderDao.addOrder(conn, order);
            }
        });
    }
}
