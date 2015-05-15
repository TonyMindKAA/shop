package com.epam.student.krynytskyi.db.dao.mysql.impl;

import com.epam.student.krynytskyi.db.dao.mysql.MySqlProductDao;
import com.epam.student.krynytskyi.db.dto.ProductDTO;
import com.epam.student.krynytskyi.db.dto.ProductDTOImpl;
import com.epam.student.krynytskyi.entity.Product;
import com.epam.student.krynytskyi.util.db.mysql.PrepareStatementBuilder;
import com.epam.student.krynytskyi.util.db.mysql.PrepareStatementBuilderImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySqlProductDaoImpl implements MySqlProductDao {
    private static final Logger log = Logger.getLogger(MySqlProductDaoImpl.class);
    private static final String SQL_COUNT_PRODUCTS = "SELECT COUNT(pr.id) AS counter FROM product as pr;";
    private ProductDTO productDTO = new ProductDTOImpl();

    @Override
    public List<Product> getByParams(Connection conn, PrepareStatementBuilder statementBuilder) throws Exception {
        String sqlQuery = statementBuilder.getQuery();
        log.debug(sqlQuery);
        List<Product> products = new ArrayList<>();
        try (PreparedStatement prst = conn.prepareStatement(sqlQuery)) {
            statementBuilder.build(prst);
            prst.executeQuery();
            ResultSet resSet = prst.getResultSet();
            while (resSet.next()) {
                addProductToList(products, resSet);
            }
        } catch (Exception e) {
            return throwExceptionUp(e);
        }
        return products;
    }

    private void addProductToList(List<Product> products, ResultSet resSet) throws Exception {
        products.add(productDTO.extract(resSet));
    }

    @Override
    public int countProducts(Connection conn, PrepareStatementBuilder statementBuilder) throws Exception {
        String sqlQuery = statementBuilder.getQuery();
        try (PreparedStatement prst = conn.prepareStatement(sqlQuery)) {
            statementBuilder.build(prst);
            prst.execute();
            ResultSet resSet = prst.getResultSet();
            if (resSet.next())
                return resSet.getInt("numberProduct");
        } catch (Exception e) {
            throwExceptionUp(e);
        }
        return 0;
    }

    private List<Product> throwExceptionUp(Exception e) throws Exception {
        String message = logException(e);
        throw new Exception(message, e);
    }

    private String logException(Exception e) {
        String message = "Can not perform query";
        log.error(message, e);
        return message;
    }

}
