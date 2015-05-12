package com.epam.student.krynytskyi.service;

import com.epam.student.krynytskyi.beans.PrepareStatementBuilderParamsBean;
import com.epam.student.krynytskyi.beans.ProductFormBean;
import com.epam.student.krynytskyi.db.dao.mysql.MySqlProductDao;
import com.epam.student.krynytskyi.db.dao.mysql.impl.MySqlProductDaoImpl;
import com.epam.student.krynytskyi.db.transaction.TransactionManager;
import com.epam.student.krynytskyi.db.transaction.TransactionManagerImpl;
import com.epam.student.krynytskyi.db.transaction.TransactionOperation;
import com.epam.student.krynytskyi.entity.Product;
import com.epam.student.krynytskyi.util.db.mysql.PrepareStatementBuilderImpl;
import com.epam.student.krynytskyi.util.db.mysql.SQLCountProductByParamPrepareStatementBuilderParamGenerator;
import com.epam.student.krynytskyi.util.db.mysql.SQLSelectByParamPrepareStatementBuilderParamGenerator;

import java.sql.Connection;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private TransactionManager transactionManager = new TransactionManagerImpl();
    private MySqlProductDao mySqlProductDao = new MySqlProductDaoImpl();

    @Override
    public int countProducts(ProductFormBean productFormBean) throws Exception {
        SQLCountProductByParamPrepareStatementBuilderParamGenerator paramGenerator = new SQLCountProductByParamPrepareStatementBuilderParamGenerator();
        PrepareStatementBuilderParamsBean builderParams = paramGenerator.generate(productFormBean);
        final PrepareStatementBuilderImpl statementBuilder = new PrepareStatementBuilderImpl(builderParams);
        return (int) transactionManager.doInTransaction(new TransactionOperation() {
            @Override
            public Object execute(final Connection conn) throws Exception {
                return mySqlProductDao.countProducts(conn, statementBuilder);
            }
        });
    }


    @Override
    public List<Product> getByParams(final ProductFormBean productFormBean) throws Exception {
        SQLSelectByParamPrepareStatementBuilderParamGenerator paramGenerator =
                new SQLSelectByParamPrepareStatementBuilderParamGenerator();
        PrepareStatementBuilderParamsBean builderParams = paramGenerator.generate(productFormBean);
        final PrepareStatementBuilderImpl statementBuilder = new PrepareStatementBuilderImpl(builderParams);
        return (List<Product>) transactionManager.doInTransaction(new TransactionOperation() {
            @Override
            public Object execute(Connection conn) throws Exception {
                return mySqlProductDao.getByParams(conn, statementBuilder);
            }
        });
    }
}
