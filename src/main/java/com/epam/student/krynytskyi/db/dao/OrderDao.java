package com.epam.student.krynytskyi.db.dao;

import com.epam.student.krynytskyi.entity.Order;

import java.sql.Connection;

public interface OrderDao {

    Order addOrder(Connection conn, Order order) throws Exception;
}
