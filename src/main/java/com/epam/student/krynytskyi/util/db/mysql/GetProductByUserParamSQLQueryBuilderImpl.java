package com.epam.student.krynytskyi.util.db.mysql;

import com.epam.student.krynytskyi.beans.ProductFormParamBean;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class GetProductByUserParamSQLQueryBuilderImpl implements GetProductByUserParamSQLQueryBuilder {
    private static final String FIRST_PART_SQL_GET_BY_PARAMS = "SELECT *, pr.id as pr_id  FROM shop.manufacturer as mn, shop.product_type as prt, shop.product as pr where pr.product_type_id = prt.id and pr.manufacture_id = mn.id";
    private static final String LAST_PART_SQL_GET_BY_PARAMS = ";";
    private static final String SQL_COUNT_PRODUCTS = "SELECT COUNT(pr.id) AS counter FROM product as pr;";

    @Override
    public String build(Map<String, String> sqlQueryParts,
                                  List<ProductFormParamBean> params) {
        checkArguments(sqlQueryParts, params);
        String sqlQuery = FIRST_PART_SQL_GET_BY_PARAMS;
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
        checkArgument(param != null, "parameters must not be null");
        checkArgument(!sqlQueryParts.isEmpty(), "sqlQueryParts must not be empty");
    }
}
