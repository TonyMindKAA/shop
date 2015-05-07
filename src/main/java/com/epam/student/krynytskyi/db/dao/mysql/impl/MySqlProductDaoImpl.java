package com.epam.student.krynytskyi.db.dao.mysql.impl;

import static com.google.common.base.Preconditions.checkArgument;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.student.krynytskyi.beans.ProductFormParamBean;
import com.epam.student.krynytskyi.db.dao.mysql.MySqlProductDao;
import com.epam.student.krynytskyi.entity.Manufacture;
import com.epam.student.krynytskyi.entity.Product;
import com.epam.student.krynytskyi.entity.ProductType;

public class MySqlProductDaoImpl implements MySqlProductDao {
	private static final Logger log = Logger
			.getLogger(MySqlProductDaoImpl.class);
	private static final String FRIST_PART_SQL_GET_BY_PARAMS = "SELECT *, pr.id as pr_id  FROM shop.manufacturer as mn, shop.product_type as prt, shop.product as pr where pr.product_type_id = prt.id and pr.manufacture_id = mn.id";
	private static final String LAST_PART_SQL_GET_BY_PARAMS = ";";
	private static final String SQL_COUNT_PRODUCTS = "SELECT COUNT(pr.id) AS counter FROM product as pr;";

	@Override
	public List<Product> getByParams(Connection conn,
			Map<String, String> sqlQueryParts, List<ProductFormParamBean> params)
			throws Exception {
		checkArguments(sqlQueryParts, params);
		String sqlQuery = createSqlQuery(sqlQueryParts, params);
		Product product = null;
		List<Product> products = new ArrayList<>();
		try (PreparedStatement prst = conn.prepareStatement(sqlQuery)) {
			initPreparedStatement(params, prst);
			prst.execute();
			ResultSet resSet = prst.getResultSet();
			while (resSet.next()) {
				product = extractProductDTO(resSet);
				products.add(product);
			}
		} catch (Exception e) {
			String message = "Can not perform query";
			log.error(message, e);
			throw new Exception(message, e);
		}
		return products;
	}

	private Product extractProductDTO(ResultSet resSet) throws Exception {
		Product product = new Product();
		ProductType productType = new ProductType();
		Manufacture manufacture = new Manufacture();
		productType.setId(resSet.getInt("product_type_id"));
		productType.setType(resSet.getString("type"));
		manufacture.setManufacture("manufacture");
		manufacture.setId(resSet.getInt("manufacture_id"));
		product.setDisription_en(resSet.getString("disription_en"));
		product.setDisription_ru(resSet.getString("disription_ru"));
		product.setId(resSet.getString("pr_id"));
		product.setImg(resSet.getString("img"));
		product.setName(resSet.getString("name"));
		product.setPrice(resSet.getInt("price"));
		product.setManufacture(manufacture);
		product.setProductType(productType);
		return product;
	}

	private void initPreparedStatement(List<ProductFormParamBean> params,
			PreparedStatement prst) throws SQLException {
		for (int i = 1; i == params.size(); i++) {
			prst.setString(i, params.get(i - 1).getValue());
		}
	}

	private String createSqlQuery(Map<String, String> sqlQueryParts,
			List<ProductFormParamBean> params) {
		String sqlQuery = FRIST_PART_SQL_GET_BY_PARAMS;
		for (ProductFormParamBean param : params) {
			String partOfQuery = sqlQueryParts.get(param.getName());
			if (partOfQuery != null)
				sqlQuery += partOfQuery;
		}
		return sqlQuery += LAST_PART_SQL_GET_BY_PARAMS;
	}

	private void checkArguments(Map<String, String> sqlQueryParts,
			List<ProductFormParamBean> param) {
		checkArgument(sqlQueryParts != null, "sqlQueryParts must not be null");
		checkArgument(param != null, "sqlQueryParts must not be null");
		checkArgument(sqlQueryParts.isEmpty(),
				"sqlQueryParts must not be empty");
		checkArgument(param.isEmpty(), "sqlQueryParts must not be empty");
	}

	@Override
	public int countProducts(Connection conn) throws Exception {
		try (PreparedStatement prst = conn.prepareStatement(SQL_COUNT_PRODUCTS)) {
			prst.execute();
			ResultSet resSet = prst.getResultSet();
			if (resSet.next())
				return resSet.getInt("counter");
		} catch (Exception e) {
			String message = "Can not perform query";
			log.error(message, e);
			throw new Exception(message, e);
		}
		return 0;
	}

}
