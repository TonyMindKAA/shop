package com.epam.student.krynytskyi.util.db.mysql;

import com.epam.student.krynytskyi.beans.PrepareStatementBuilderParamsBean;
import com.epam.student.krynytskyi.beans.ProductFacetQueryData;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.epam.student.krynytskyi.util.number.NumberUtil.isNumber;

public class ProductAmountQueryGenerator {

    private static final Logger log = Logger.getLogger(ProductQueryGenerator.class);
    private static final String EMPTY = "";
    private static final String END_OF_LINE = ";";

    private String BASE_QUERY = "SELECT COUNT(pr.id) numberProduct " +
            "FROM shop.product as pr " +
            "INNER JOIN shop.product_type as prt ON pr.product_type_id = prt.id " +
            "INNER JOIN shop.manufacturer as mn ON pr.manufacture_id = mn.id " +
            "WHERE ";
    private String sqlPriceTo = " AND pr.price <= ?";
    private String sqlPriceFrom = " AND pr.price >= ?";
    private String sqlTittle = " pr.name LIKE '%' ? '%'";
    private String sqlType = " prt.type = ?";
    private String sqlManufacturer = "mn.manufacturer = ?";
    private String priceSQLPart = EMPTY;
    private String typeSQLPart = EMPTY;
    private String manufacturerSQLPart = EMPTY;
    private String tittleSQLPart = EMPTY;
    private String resultSQLQuery = EMPTY;
    private List<String> paramValues = new ArrayList<>();


    public PrepareStatementBuilderParamsBean generate(ProductFacetQueryData productFormBean) {
        generateTittle(productFormBean);
        generatePricePart(productFormBean);
        generateProductType(productFormBean);
        generateProductManufacture(productFormBean);
        stickTogetherAllSQLPartToResultSQLQuery();
        PrepareStatementBuilderParamsBean prepareStatementBuilderParamsBean = generatePrepareStatementBuilderParams();
        toDefaultValue();
        return prepareStatementBuilderParamsBean;
    }

    private void generateTittle(ProductFacetQueryData productFormBean) {
        if (isParameterExist(productFormBean.getTitle())) {
            tittleSQLPart = sqlTittle;
            addParamValue(productFormBean.getTitle());
        } else {
            tittleSQLPart = " pr.name LIKE '%%'";
        }
    }

    private void generatePricePart(ProductFacetQueryData productFormBean) {
        if (isParameterExist(productFormBean.getPriceFrom()) && isNumber(productFormBean.getPriceFrom())) {
            priceSQLPart += sqlPriceFrom;
            addParamValue(productFormBean.getPriceFrom());
        }
        if (isParameterExist(productFormBean.getPriceTo())&& isNumber(productFormBean.getPriceTo())) {
            priceSQLPart += sqlPriceTo;
            addParamValue(productFormBean.getPriceTo());
        }
    }

    private PrepareStatementBuilderParamsBean generatePrepareStatementBuilderParams() {
        PrepareStatementBuilderParamsBean builderParamsBean = new PrepareStatementBuilderParamsBean();
        builderParamsBean.setPrepareStatementParams(paramValues);
        builderParamsBean.setSqlQuery(resultSQLQuery);
        return builderParamsBean;
    }

    private void stickTogetherAllSQLPartToResultSQLQuery() {
        resultSQLQuery = BASE_QUERY + tittleSQLPart + priceSQLPart + typeSQLPart +
                manufacturerSQLPart + END_OF_LINE;
    }

    private boolean addParamValue(String value) {
        return paramValues.add(value);
    }

    private void generateProductManufacture(ProductFacetQueryData productFormBean) {
        List<String> manufactures = productFormBean.getProductManufactures();

        if (manufactures.size() == 1) {
            manufacturerSQLPart = " AND "+sqlManufacturer;
            paramValues.addAll(manufactures);
            return;
        }

        if (manufactures.size() > 1) {
            manufacturerSQLPart = " AND( " + sqlManufacturer;
            for (int i = 0; i < manufactures.size() - 1; i++) {
                manufacturerSQLPart += " OR " + sqlManufacturer;
            }
            manufacturerSQLPart += ")";
            paramValues.addAll(manufactures);
        }
    }

    private void generateProductType(ProductFacetQueryData productFormBean) {
        List<String> types = productFormBean.getProductTypes();
        if (types.size() == 1) {
            typeSQLPart = " AND "+sqlType;
            paramValues.addAll(types);
            return;
        }
        if (types.size() > 1) {
            typeSQLPart = " AND( " + sqlType;
            for (int i = 0; i < types.size() - 1; i++) {
                typeSQLPart += " OR " + sqlType;
            }
            typeSQLPart += ")";
            paramValues.addAll(types);
        }
    }

    private boolean isParameterExist(String parameter) {
        return parameter != null && !parameter.trim().isEmpty();
    }

    private void toDefaultValue() {
        priceSQLPart = EMPTY;
        typeSQLPart = EMPTY;
        manufacturerSQLPart = EMPTY;
        tittleSQLPart = EMPTY;
        resultSQLQuery = EMPTY;
        paramValues = new ArrayList<>();
    }
}
