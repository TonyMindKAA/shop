package com.epam.student.krynytskyi.db.dao.mysql.impl;

import com.epam.student.krynytskyi.db.dao.mysql.ProductDao;
import com.epam.student.krynytskyi.db.dto.ProductDTO;
import com.epam.student.krynytskyi.db.dto.ProductDTOImpl;
import com.epam.student.krynytskyi.entity.Product;
import com.epam.student.krynytskyi.util.db.mysql.PrepareStatementBuilder;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySqlProductDao implements ProductDao {
    private static final Logger log = Logger.getLogger(MySqlProductDao.class);
    public static final String SQL_GET_PRODUCT_BY_ID = "SELECT *, pr.id as pr_id FROM shop.product as pr inner join shop.product_type as prt on pr.product_type_id = prt.id inner join shop.manufacturer as mn on pr.manufacture_id = mn.id WHERE  pr.id = ?;";
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
            throwExceptionUp(e);
        }
        return products;
    }

    @Override
    public Product getById(Connection conn, String id) throws Exception {
        Product product = null;
        try (PreparedStatement prst = conn.prepareStatement(SQL_GET_PRODUCT_BY_ID)) {
            prst.setString(1,id);
            prst.executeQuery();
            ResultSet resSet = prst.getResultSet();
            while (resSet.next()) {
                product = productDTO.extract(resSet);
            }
        } catch (Exception e) {
            throwExceptionUp(e);
        }
        return product;
    }

    private void addProductToList(List<Product> products, ResultSet resSet) throws Exception {
        products.add(productDTO.extract(resSet));
    }

    @Override
    public int countProducts(Connection conn, PrepareStatementBuilder statementBuilder) throws Exception {
        String sqlQuery = statementBuilder.getQuery();
        try (PreparedStatement prst = conn.prepareStatement(sqlQuery)) {
            statementBuilder.build(prst);
            prst.executeQuery();
            ResultSet resSet = prst.getResultSet();
            if (resSet.next())
                return resSet.getInt("numberProduct");
        } catch (Exception e) {
            throwExceptionUp(e);
        }
        return 0;
    }

    private void throwExceptionUp(Exception e) throws Exception {
        String message = logException(e);
        throw new Exception(message, e);
    }

    private String logException(Exception e) {
        String message = "Can not perform query";
        log.error(message, e);
        return message;
    }

}
