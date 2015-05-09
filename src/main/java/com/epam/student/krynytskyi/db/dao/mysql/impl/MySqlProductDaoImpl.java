package com.epam.student.krynytskyi.db.dao.mysql.impl;

import com.epam.student.krynytskyi.beans.ProductFormParamBean;
import com.epam.student.krynytskyi.db.dao.mysql.MySqlProductDao;
import com.epam.student.krynytskyi.db.dto.ProductDTO;
import com.epam.student.krynytskyi.db.dto.ProductDTOImpl;
import com.epam.student.krynytskyi.entity.Product;
import com.epam.student.krynytskyi.util.GetProductByUserParamSQLQueryBuilder;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySqlProductDaoImpl implements MySqlProductDao {
    private static final Logger log = Logger.getLogger(MySqlProductDaoImpl.class);
    private static final String SQL_COUNT_PRODUCTS = "SELECT COUNT(pr.id) AS counter FROM product as pr;";
    private ProductDTO productDTO = new ProductDTOImpl();

    @Override
    public List<Product> getByParams(Connection conn,
                                     Map<String, String> sqlQueryParts, List<ProductFormParamBean> params) throws Exception {
        String sqlQuery = buildQuery(sqlQueryParts, params);
        log.debug(sqlQuery);
        List<Product> products = new ArrayList<>();
        try (PreparedStatement prst = conn.prepareStatement(sqlQuery)) {
            executeQuery(params, prst);
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

    private void executeQuery(List<ProductFormParamBean> params, PreparedStatement prst) throws SQLException {
        initPreparedStatement(params, prst);
        prst.executeQuery();
    }

    private String buildQuery(Map<String, String> sqlQueryParts, List<ProductFormParamBean> params) {
        GetProductByUserParamSQLQueryBuilder queryBuilder = new GetProductByUserParamSQLQueryBuilder();
        return queryBuilder.build(sqlQueryParts, params);
    }

    private void initPreparedStatement(List<ProductFormParamBean> params, PreparedStatement prst) throws SQLException {
        for (int i = 1; i < params.size()+1; i++) {
            log.debug(params.size() + " :: size() "+params.get(i - 1).getValue());
            log.debug(i);
            prst.setString(i, params.get(i - 1).getValue());
        }
    }

    @Override
    public int countProducts(Connection conn) throws Exception {
        try (PreparedStatement prst = conn.prepareStatement(SQL_COUNT_PRODUCTS)) {
            prst.execute();
            ResultSet resSet = prst.getResultSet();
            if (resSet.next())
                return resSet.getInt("counter");
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
