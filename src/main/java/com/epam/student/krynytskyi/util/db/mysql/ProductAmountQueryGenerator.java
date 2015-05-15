package com.epam.student.krynytskyi.util.db.mysql;

import com.epam.student.krynytskyi.beans.PrepareStatementBuilderParamsBean;
import com.epam.student.krynytskyi.beans.ProductFacetQueryData;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

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
    private List<String> types = new ArrayList<>();
    private List<String> manufacturers = new ArrayList<>();
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
        if (isParameterExist(productFormBean.getPriceFrom())) {
            priceSQLPart += sqlPriceFrom;
            addParamValue(productFormBean.getPriceFrom());
        }
        if (isParameterExist(productFormBean.getPriceTo())) {
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
        if (isParameterExist(productFormBean.getNokia())) {
            manufacturers.add("nokia");
        }
        if (isParameterExist(productFormBean.getSigma())) {
            manufacturers.add("sigma");
        }
        if (isParameterExist(productFormBean.getApple())) {
            manufacturers.add("apple");
        }

        if (manufacturers.size() == 1) {
            manufacturerSQLPart = " AND "+sqlManufacturer;
            paramValues.addAll(manufacturers);
            return;
        }

        if (manufacturers.size() > 1) {
            manufacturerSQLPart = " AND( " + sqlManufacturer;
            for (int i = 0; i < manufacturers.size() - 1; i++) {
                manufacturerSQLPart += " OR " + sqlManufacturer;
            }
            manufacturerSQLPart += ")";
            paramValues.addAll(manufacturers);
        }
    }

    private void generateProductType(ProductFacetQueryData productFormBean) {
        if (isParameterExist(productFormBean.getAmbientType())) {
            types.add("AMBIENT");
        }
        if (isParameterExist(productFormBean.getProtectedType())) {
            types.add("PROTECTED");
        }
        if (isParameterExist(productFormBean.getCheapType())) {
            types.add("CHEAP");
        }
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
        log.debug(parameter);
        return parameter != null && !parameter.isEmpty();
    }

    private void toDefaultValue() {
        types = new ArrayList<>();
        manufacturers = new ArrayList<>();
        priceSQLPart = EMPTY;
        typeSQLPart = EMPTY;
        manufacturerSQLPart = EMPTY;
        tittleSQLPart = EMPTY;
        resultSQLQuery = EMPTY;
        paramValues = new ArrayList<>();
    }
}
