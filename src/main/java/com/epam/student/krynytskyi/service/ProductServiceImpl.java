package com.epam.student.krynytskyi.service;

import com.epam.student.krynytskyi.beans.ProductFormParamBean;
import com.epam.student.krynytskyi.db.dao.mysql.MySqlProductDao;
import com.epam.student.krynytskyi.db.dao.mysql.impl.MySqlProductDaoImpl;
import com.epam.student.krynytskyi.db.transaction.TransactionManager;
import com.epam.student.krynytskyi.db.transaction.TransactionManagerImpl;
import com.epam.student.krynytskyi.db.transaction.TransactionOperation;
import com.epam.student.krynytskyi.entity.Product;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
    private TransactionManager transactionManager = new TransactionManagerImpl();
    private MySqlProductDao mySqlProductDao = new MySqlProductDaoImpl();
    private Map<String, String> sqlQueryParts;

    public ProductServiceImpl(Map<String, String> sqlQueryParts) {
        this.sqlQueryParts = sqlQueryParts;
    }

    @Override
    public int countProducts() throws Exception {
        return (int) transactionManager.doInTransaction(new TransactionOperation() {
            @Override
            public Object execute(Connection conn) throws Exception {
                return mySqlProductDao.countProducts(conn);
            }
        });
    }

    @Override
    public List<Product> getByParams(final List<ProductFormParamBean> param) throws Exception {
        return (List<Product>) transactionManager.doInTransaction(new TransactionOperation() {
            @Override
            public Object execute(Connection conn) throws Exception {
                return mySqlProductDao.getByParams(conn,sqlQueryParts, param);
            }
        });
    }
}
