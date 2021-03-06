package com.epam.student.krynytskyi.util.db.mysql;

import com.epam.student.krynytskyi.beans.PrepareStatementBuilderParamsBean;
import com.epam.student.krynytskyi.beans.ProductFacetQueryData;
import com.epam.student.krynytskyi.db.constant.ProductOrderConst;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.epam.student.krynytskyi.util.number.NumberUtil.isNumber;


public class ProductQueryGenerator {
    private static final Logger log = Logger.getLogger(ProductQueryGenerator.class);
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
    private String priceSQLPart = "";
    private String typeSQLPart = "";
    private String manufacturerSQLPart = "";
    private String orderSQLPart = "";
    private String limitSQLPart = "";
    private String tittleSQLPart = "";
    private String resultSQLQuery = "";
    private List<String> paramValues = new ArrayList<>();

    public PrepareStatementBuilderParamsBean generate(ProductFacetQueryData productFormBean) {
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

    private void generateLimit(ProductFacetQueryData facetQueryData) {
        if (isParameterExist(facetQueryData.getNumberItems()) && isParameterExist(facetQueryData.getCurrentPage())) {
            int currentPage = getCurrentPage(facetQueryData);
            int pageSize = getPageSize(facetQueryData);
            int offset = (currentPage - 1) * pageSize;
            limitSQLPart = sqlLimit + " " + offset + ", " + pageSize;
        }
    }

    private int getCurrentPage(ProductFacetQueryData facetQueryData) {
        int currentPage = convertToNum(facetQueryData.getCurrentPage());
        if(currentPage <= 0) {
            return 1;
        }
        return currentPage;
    }

    private int getPageSize(ProductFacetQueryData facetQueryData) {
        int number = convertToNum(facetQueryData.getNumberItems());
        if(number <= 0) {
            return 5;
        }
        return number;
    }

    private int convertToNum(String currentPageStr) {
        try {
            return Integer.parseInt(currentPageStr);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void generateProductOrder(ProductFacetQueryData productFormBean) {
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

    private void generateTittle(ProductFacetQueryData productFormBean) {
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

    private void generateProductManufacture(ProductFacetQueryData productFormBean) {
        List<String> manufactures = productFormBean.getProductManufactures();
        if (manufactures.size() == 1) {
            manufacturerSQLPart = " AND " + sqlManufacturer;
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

    private void generatePricePart(ProductFacetQueryData productFormBean) {
        if (isParameterExist(productFormBean.getPriceFrom()) && isNumber(productFormBean.getPriceFrom())) {
            priceSQLPart += sqlPriceFrom;
            addParamValue(productFormBean.getPriceFrom());
        }
        if (isParameterExist(productFormBean.getPriceTo()) && isNumber(productFormBean.getPriceTo())) {
            priceSQLPart += sqlPriceTo;
            addParamValue(productFormBean.getPriceTo());
        }
    }

    private boolean isParameterExist(String parameter) {
        return parameter != null && !parameter.trim().isEmpty();
    }
}
