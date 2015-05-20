package com.epam.student.krynytskyi.db.dao;

import com.epam.student.krynytskyi.entity.OrderEntry;

import java.util.List;

public interface OrderEntryDao {
    void addOrderEntryByOrderEntryList(List<OrderEntry> orderEntries) throws Exception;
}
