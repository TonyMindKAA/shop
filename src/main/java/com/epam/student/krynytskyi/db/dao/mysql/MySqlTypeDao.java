package com.epam.student.krynytskyi.db.dao.mysql;

import com.epam.student.krynytskyi.db.dao.TypeDao;
import com.epam.student.krynytskyi.entity.Product;
import com.epam.student.krynytskyi.entity.ProductType;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySqlTypeDao implements TypeDao {
    private static final Logger log = Logger.getLogger(MySqlTypeDao.class);
    public static final String SQL = "SELECT * FROM shop.product_type;";

    @Override
    public List<ProductType> getAll(Connection conn) throws Exception {
        List<ProductType> productType = new ArrayList<>();
        try (PreparedStatement prst = conn.prepareStatement(SQL)) {
            prst.execute();
            ResultSet resSet = prst.getResultSet();
            while (resSet.next()) {
                ProductType newManufacture = new ProductType();
                newManufacture.setId(resSet.getInt("id"));
                newManufacture.setType(resSet.getString("type"));
                productType.add(newManufacture);
            }
        } catch (Exception e) {
            throwExceptionUp(e);
        }
        return  productType;
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
