package com.epam.student.krynytskyi.service.impl;

import com.epam.student.krynytskyi.db.dao.mysql.TypeDao;
import com.epam.student.krynytskyi.db.dao.mysql.impl.MySqlTypeDao;
import com.epam.student.krynytskyi.db.transaction.TransactionManager;
import com.epam.student.krynytskyi.db.transaction.TransactionManagerImpl;
import com.epam.student.krynytskyi.db.transaction.TransactionOperation;
import com.epam.student.krynytskyi.entity.ProductType;
import com.epam.student.krynytskyi.service.TypeService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class TypeServiceImpl implements TypeService {
    private static final Logger log = Logger.getLogger(TypeServiceImpl.class);
    private TransactionManager transactionManager = new TransactionManagerImpl();
    TypeDao typeDao = new MySqlTypeDao();

    @Override
    public List<ProductType> getAll() throws Exception {
        return (List<ProductType>) transactionManager
                .doInTransaction(new TransactionOperation() {
                    @Override
                    public Object execute(Connection conn) throws Exception {
                        return typeDao.getAll(conn);
                    }
                });
    }
}
