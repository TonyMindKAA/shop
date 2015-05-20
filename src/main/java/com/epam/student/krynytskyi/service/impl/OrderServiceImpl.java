package com.epam.student.krynytskyi.service.impl;

import com.epam.student.krynytskyi.db.dao.OrderDao;
import com.epam.student.krynytskyi.db.dao.OrderEntryDao;
import com.epam.student.krynytskyi.db.dao.mysql.MySqlOrderDao;
import com.epam.student.krynytskyi.db.dao.mysql.MySqlOrderEntryDao;
import com.epam.student.krynytskyi.db.transaction.TransactionManager;
import com.epam.student.krynytskyi.db.transaction.TransactionManagerImpl;
import com.epam.student.krynytskyi.db.transaction.TransactionOperation;
import com.epam.student.krynytskyi.entity.Order;
import com.epam.student.krynytskyi.entity.OrderEntry;
import com.epam.student.krynytskyi.service.OrderService;

import java.sql.Connection;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private TransactionManager transactionManager = new TransactionManagerImpl();
    private OrderDao orderDao = new MySqlOrderDao();
    private OrderEntryDao orderEntryDao = new MySqlOrderEntryDao();

    @Override
    public Order addOrder(final Order order) throws Exception {
        return addOrderToDB(order);
    }

    private List<OrderEntry> setOrderIdToOrderEntry(Order orderWithId) {
        int orderId = orderWithId.getOrderId();
        List<OrderEntry> ordersEntry = orderWithId.getOrdersEntry();
        for (int i = 0; i < ordersEntry.size(); i++) {
            ordersEntry.get(i).setOrderId(orderId);
        }
        return ordersEntry;
    }

    private Order addOrderToDB(final Order order) throws Exception {
        return (Order) transactionManager.doInTransaction(new TransactionOperation() {
            @Override
            public Object execute(Connection conn) throws Exception {
                Order orderWithId = orderDao.addOrder(conn, order);
                orderEntryDao.insertOrderEntryByOrderEntryList(conn, setOrderIdToOrderEntry(orderWithId));
                return orderWithId;

            }
        });
    }
}
