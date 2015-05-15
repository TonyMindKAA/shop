package com.epam.student.krynytskyi.db.dao.mysql.impl;

import com.epam.student.krynytskyi.db.dao.mysql.ManufactureDAO;
import com.epam.student.krynytskyi.entity.Manufacture;
import com.epam.student.krynytskyi.entity.Product;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySqlManufactureDAO implements ManufactureDAO {
    private static final Logger log = Logger.getLogger(MySqlManufactureDAO.class);
    public static final String SQL = "SELECT * FROM shop.manufacturer;";

    @Override
    public List<Manufacture> getAll(Connection conn) throws Exception {
        List<Manufacture> manufactures = new ArrayList<>();
        try (PreparedStatement prst = conn.prepareStatement(SQL)) {
            prst.execute();
            ResultSet resSet = prst.getResultSet();
            while (resSet.next()) {
                Manufacture newManufacture = new Manufacture();
                newManufacture.setId(resSet.getInt("id"));
                newManufacture.setManufacture(resSet.getString("manufacturer"));
                manufactures.add(newManufacture);
            }
        } catch (Exception e) {
            throwExceptionUp(e);
        }
        return manufactures;
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
