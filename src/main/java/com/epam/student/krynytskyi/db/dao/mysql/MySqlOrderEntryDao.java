package com.epam.student.krynytskyi.db.dao.mysql;

import com.epam.student.krynytskyi.db.dao.OrderEntryDao;
import com.epam.student.krynytskyi.db.dao.exception.DAOException;
import com.epam.student.krynytskyi.entity.OrderEntry;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MySqlOrderEntryDao implements OrderEntryDao {

    public static final String SQL_INSERT_ORDER_ENTRY = "INSERT INTO `shop`.`order_entry` (`order_id`, `product_id`, `quantity`, `price`) VALUES (?,?, ?, ?);";
    private static final Logger log = Logger.getLogger(MySqlOrderEntryDao.class);

    @Override
    public void insertOrderEntryByOrderEntryList(Connection conn, List<OrderEntry> orderEntries) throws Exception {
        try (PreparedStatement pstmt = conn
                .prepareStatement(SQL_INSERT_ORDER_ENTRY)) {
            packOrderEntryDTO(orderEntries, pstmt);
            pstmt.executeBatch();
        } catch (Exception e) {
            String message = "Can not perform query";
            log.error(message, e);
            throw new DAOException(message, e);
        }
    }

    private void packOrderEntryDTO(List<OrderEntry> orderEntries, PreparedStatement pstmt) throws SQLException {
        for (int i = 0; i < orderEntries.size(); i++) {
            pstmt.setInt(1,orderEntries.get(i).getOrderId());
            pstmt.setString(2,orderEntries.get(i).getProduct().getId());
            pstmt.setInt(3,orderEntries.get(i).getQuantity());
            pstmt.setLong(4,orderEntries.get(i).getPrice());
            pstmt.addBatch();
        }
    }
}
