package com.epam.student.krynytskyi.db.dao.mysql;

import com.epam.student.krynytskyi.db.dao.OrderDao;
import com.epam.student.krynytskyi.db.dao.exception.DAOException;
import com.epam.student.krynytskyi.entity.Order;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Date;

public class MySqlOrderDao implements OrderDao {
    private static final Logger log = Logger.getLogger(MySqlOrderDao.class);
    public static final String SQL_INSERT_ORDER = "INSERT INTO `shop`.`order` (`user_id`, `shipping_address`, `create_on`, `shipping_status_id`," +
            " `total_price`, `bank_card`, `delivery_type_id`," +
            " `quantity`, `shipping_status_description`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    @Override
    public Order addOrder(Connection conn, Order order) throws Exception {
        try (PreparedStatement pstmt = conn
                .prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            packOrderDTO(order, pstmt);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            int auto_id = rs.getInt(1);
            order.setOrderId(auto_id);
            return order;
        } catch (Exception e) {
            String message = "Can not perform query";
            log.error(message, e);
            throw new DAOException(message, e);
        }
    }

    private void packOrderDTO(Order order, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, order.getUser().getId());
        pstmt.setString(2, order.getShippingAddress());
        Date createOn = order.getCreateOn();
        pstmt.setDate(3, new java.sql.Date(createOn.getTime()));
        pstmt.setInt(4, order.getShippingStatus().getShippingStatusId());
        pstmt.setLong(5, order.getTotalPrice());
        pstmt.setString(6, order.getBankCard());
        pstmt.setInt(7, order.getDeliveryType().getDeliveryTypeId());
        pstmt.setInt(8, order.getQuantity());
        pstmt.setString(9, order.getShippingStatusDescription());
    }
}
