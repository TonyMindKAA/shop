package com.epam.student.krynytskyi.util.db.mysql;

import com.epam.student.krynytskyi.beans.PrepareStatementBuilderParamsBean;
import com.epam.student.krynytskyi.beans.ProductFormBean;
import com.epam.student.krynytskyi.db.constant.ProductOrderConst;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class SQLSelectByParamPrepareStatementBuilderParamGenerator {
    private static final Logger log = Logger.getLogger(SQLSelectByParamPrepareStatementBuilderParamGenerator.class);
    private String SELECT_PRODUCT_BY_PARAMETER = "SELECT *, pr.id as pr_id " +
            "FROM shop.product as pr " +
            "inner join shop.product_type as prt on pr.product_type_id = prt.id " +
            "inner join shop.manufacturer as mn on pr.manufacture_id = mn.id " +
            "WHERE ";
    private String sqlPriceTo = " AND pr.price <= ?";
    private String sqlPriceFrom = " AND pr.price >= ?";
    private String sqlTittle = " pr.name LIKE '%' ? '%'";
    private String sqlType = " prt.type = ?";
    private String sqlManufacturer = "mn.manufacturer = ?";
    private String sqlOrder = " order by";
    private String sqlLimit = " limit";
    private List<String> types = new ArrayList<>();
    private List<String> manufacturers = new ArrayList<>();
    private String priceSQLPart = "";
    private String typeSQLPart = "";
    private String manufacturerSQLPart = "";
    private String orderSQLPart = "";
    private String limitSQLPart = "";
    private String tittleSQLPart = "";
    private String resultSQLQuery = "";
    private List<String> paramValues = new ArrayList<>();

    public PrepareStatementBuilderParamsBean generate(ProductFormBean productFormBean) {
        generateTittle(productFormBean);
        generatePricePart(productFormBean);
        generateProductType(productFormBean);
        generateProductManufacture(productFormBean);
        generateProductOrder(productFormBean);
        generateLimit(productFormBean);
        stickTogetherAllSQLPartToResultSQLQuery();
        PrepareStatementBuilderParamsBean prepareStatementBuilderParamsBean = generatePrepareStatementBuilderParams();
        toDefaultValue();
        return prepareStatementBuilderParamsBean;
    }

    private void toDefaultValue() {
        types = new ArrayList<>();
        manufacturers = new ArrayList<>();
        priceSQLPart = "";
        typeSQLPart = "";
        manufacturerSQLPart = "";
        orderSQLPart = "";
        limitSQLPart = "";
        tittleSQLPart = "";
        resultSQLQuery = "";
        paramValues = new ArrayList<>();
    }

    private PrepareStatementBuilderParamsBean generatePrepareStatementBuilderParams() {
        PrepareStatementBuilderParamsBean builderParamsBean = new PrepareStatementBuilderParamsBean();
        builderParamsBean.setPrepareStatementParams(paramValues);
        builderParamsBean.setSqlQuery(resultSQLQuery);
        return builderParamsBean;
    }

    private void stickTogetherAllSQLPartToResultSQLQuery() {
        resultSQLQuery = SELECT_PRODUCT_BY_PARAMETER + tittleSQLPart + priceSQLPart + typeSQLPart +
                manufacturerSQLPart + orderSQLPart + limitSQLPart + ";";
    }

    private void generateLimit(ProductFormBean productFormBean) {
        if (isParameterExist(productFormBean.getNumberItems()) && isParameterExist(productFormBean.getCurrentPage())) {
            String numberItemsStr = productFormBean.getNumberItems();
            String currentPageStr = productFormBean.getCurrentPage();
            int currentPage = convertToNum(currentPageStr);
            int numberItems = convertToNum(numberItemsStr);
            limitSQLPart = sqlLimit + " " + (currentPage * numberItems) + ", " + numberItems;
        }
    }

    private int convertToNum(String currentPageStr) {
        try {
            return Integer.parseInt(currentPageStr);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void generateProductOrder(ProductFormBean productFormBean) {
        if (isParameterExist(productFormBean.getOrder())) {
            if (productFormBean.getOrder().equals(ProductOrderConst.MANUFACTURE_A_TO_Z)) {
                orderSQLPart += sqlOrder + " mn.manufacturer";
                return;
            }
            if (productFormBean.getOrder().equals(ProductOrderConst.MANUFACTURE_Z_TO_A)) {
                orderSQLPart += sqlOrder + " mn.manufacturer DESC";
                return;
            }
            if (productFormBean.getOrder().equals(ProductOrderConst.PRICE_HEIGHT_LOW)) {
                orderSQLPart += sqlOrder + " pr.price";
                return;
            }
            if (productFormBean.getOrder().equals(ProductOrderConst.PRICE_LOW_HEIGHT)) {
                orderSQLPart += sqlOrder + " pr.price DESC";
                return;
            }
            if (productFormBean.getOrder().equals(ProductOrderConst.TYPE_A_TO_Z)) {
                orderSQLPart += sqlOrder + " prt.type";
                return;
            }
            if (productFormBean.getOrder().equals(ProductOrderConst.TYPE_Z_TO_A)) {
                orderSQLPart += sqlOrder + " prt.type DESC";
            }
        }
    }

    private void generateTittle(ProductFormBean productFormBean) {
        if (isParameterExist(productFormBean.getTitle())) {
            tittleSQLPart = sqlTittle;
            addParamValue(productFormBean.getTitle());
        } else {
            tittleSQLPart = " pr.name LIKE '%%'";
        }
    }

    private boolean addParamValue(String value) {
        return paramValues.add(value);
    }

    private void generateProductManufacture(ProductFormBean productFormBean) {
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
            manufacturerSQLPart = " AND " + sqlManufacturer;
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

    private void generateProductType(ProductFormBean productFormBean) {
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
            typeSQLPart = " AND " + sqlType;
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

    private void generatePricePart(ProductFormBean productFormBean) {
        if (isParameterExist(productFormBean.getPriceFrom())) {
            priceSQLPart += sqlPriceFrom;
            addParamValue(productFormBean.getPriceFrom());
        }
        if (isParameterExist(productFormBean.getPriceTo())) {
            priceSQLPart += sqlPriceTo;
            addParamValue(productFormBean.getPriceTo());
        }
    }

    private boolean isParameterExist(String parameter) {
        log.debug(parameter);
        return parameter != null && !parameter.isEmpty();
    }
}
