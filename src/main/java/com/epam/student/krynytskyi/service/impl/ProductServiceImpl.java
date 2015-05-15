package com.epam.student.krynytskyi.service.impl;

import com.epam.student.krynytskyi.beans.PrepareStatementBuilderParamsBean;
import com.epam.student.krynytskyi.beans.ProductFacetQueryData;
import com.epam.student.krynytskyi.db.dao.mysql.ProductDao;
import com.epam.student.krynytskyi.db.dao.mysql.impl.MySqlProductDao;
import com.epam.student.krynytskyi.db.transaction.TransactionManager;
import com.epam.student.krynytskyi.db.transaction.TransactionManagerImpl;
import com.epam.student.krynytskyi.db.transaction.TransactionOperation;
import com.epam.student.krynytskyi.entity.Product;
import com.epam.student.krynytskyi.service.ProductService;
import com.epam.student.krynytskyi.util.db.mysql.PrepareStatementBuilder;
import com.epam.student.krynytskyi.util.db.mysql.PrepareStatementBuilderImpl;
import com.epam.student.krynytskyi.util.db.mysql.ProductAmountQueryGenerator;
import com.epam.student.krynytskyi.util.db.mysql.ProductQueryGenerator;

import java.sql.Connection;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private TransactionManager transactionManager = new TransactionManagerImpl();
    private ProductDao mySqlProductDao = new MySqlProductDao();

    @Override
    public int countProducts(ProductFacetQueryData productFormBean) throws Exception {
        ProductAmountQueryGenerator queryParamGenerator = new ProductAmountQueryGenerator();
        PrepareStatementBuilderParamsBean queryParams = queryParamGenerator.generate(productFormBean);
        final PrepareStatementBuilder statementBuilder = new PrepareStatementBuilderImpl(queryParams);
        return (int) transactionManager.doInTransaction(new TransactionOperation() {
            @Override
            public Object execute(final Connection conn) throws Exception {
                return mySqlProductDao.countProducts(conn, statementBuilder);
            }
        });
    }


    @Override
    public List<Product> getByParams(final ProductFacetQueryData productFormBean) throws Exception {
        ProductQueryGenerator paramGenerator = new ProductQueryGenerator();
        PrepareStatementBuilderParamsBean queryParams = paramGenerator.generate(productFormBean);
        final PrepareStatementBuilder statementBuilder = new PrepareStatementBuilderImpl(queryParams);
        return (List<Product>) transactionManager.doInTransaction(new TransactionOperation() {
            @Override
            public Object execute(Connection conn) throws Exception {
                return mySqlProductDao.getByParams(conn, statementBuilder);
            }
        });
    }
}
