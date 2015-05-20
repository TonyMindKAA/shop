package com.epam.student.krynytskyi.service.impl;

import com.epam.student.krynytskyi.db.dao.ManufactureDAO;
import com.epam.student.krynytskyi.db.dao.mysql.MySqlManufactureDAO;
import com.epam.student.krynytskyi.db.transaction.TransactionManager;
import com.epam.student.krynytskyi.db.transaction.TransactionManagerImpl;
import com.epam.student.krynytskyi.db.transaction.TransactionOperation;
import com.epam.student.krynytskyi.entity.Manufacture;
import com.epam.student.krynytskyi.service.ManufactureService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class ManufactureServiceImpl implements ManufactureService {
    private static final Logger log = Logger.getLogger(ManufactureServiceImpl.class);
    private TransactionManager transactionManager = new TransactionManagerImpl();
    private ManufactureDAO manufactureDAO = new MySqlManufactureDAO();

    @Override
    public List<Manufacture> getAll() throws Exception {
        return (List<Manufacture>) transactionManager
                .doInTransaction(new TransactionOperation() {
                    @Override
                    public Object execute(Connection conn) throws Exception {
                        return manufactureDAO.getAll(conn);
                    }
                });
    }
}
