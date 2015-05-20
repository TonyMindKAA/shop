package com.epam.student.krynytskyi.db.dao;

import com.epam.student.krynytskyi.entity.OrderEntry;

import java.sql.Connection;
import java.util.List;

public interface OrderEntryDao {
    void insertOrderEntryByOrderEntryList(Connection conn, List<OrderEntry> orderEntries) throws Exception;
}
