package com.epam.student.krynytskyi.util.db.mysql;

import com.epam.student.krynytskyi.beans.ProductFormParamBean;

import java.util.List;
import java.util.Map;

public interface GetProductByUserParamSQLQueryBuilder {
    String build(Map<String, String> sqlQueryParts, List<ProductFormParamBean> params);
}
