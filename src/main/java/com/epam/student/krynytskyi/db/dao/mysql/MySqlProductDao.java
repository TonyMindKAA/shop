package com.epam.student.krynytskyi.db.dao.mysql;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.epam.student.krynytskyi.beans.ProductFormParamBean;
import com.epam.student.krynytskyi.entity.Product;

public interface MySqlProductDao {
	int countProducts(Connection conn) throws Exception;

	List<Product> getByParams(Connection conn,Map<String, String> sqlQueryParts, List<ProductFormParamBean> param) throws Exception;
}
