package com.epam.student.krynytskyi.util.db.mysql;

import com.epam.student.krynytskyi.beans.PrepareStatementBuilderParamsBean;
import com.epam.student.krynytskyi.beans.ProductFacetQueryData;
import com.epam.student.krynytskyi.db.constant.ProductOrderConst;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class SQLSelectByParamPrepareStatementBuilderParamGeneratorTest {
    private ProductQueryGenerator paramGenerator;
    private static final  String DEFAULT_SQL_QUERY = "SELECT *, pr.id as pr_id FROM shop.product as pr " +
            "inner join shop.product_type as prt on pr.product_type_id = prt.id " +
            "inner join shop.manufacturer as mn on pr.manufacture_id = mn.id " +
            "WHERE  pr.name LIKE '%%';";

    @Before
    public void setUp() throws Exception {
        paramGenerator = new ProductQueryGenerator();
    }

    @Test
    public void testShouldReturnDefaultSQLQueryWhenPutProductFormBeanWithoutParameters(){
        ProductFacetQueryData productFormBean = new ProductFacetQueryData();
        productFormBean.setProductManufactures(new ArrayList<String>());
        productFormBean.setProductTypes(new ArrayList<String>());
        PrepareStatementBuilderParamsBean generate = paramGenerator.generate(productFormBean);
        String actualQuery = generate.getSqlQuery();
        Assert.assertEquals(DEFAULT_SQL_QUERY,actualQuery);
    }

    @Test
    public void testShouldReturnCorrectSQLQueryWhenPutProductFormBeanWithCurrentPageAndNumberItemsAndOrderParameters(){
        String expectedSqlQuery = "SELECT *, pr.id as pr_id FROM shop.product as pr " +
                "inner join shop.product_type as prt on pr.product_type_id = prt.id " +
                "inner join shop.manufacturer as mn on pr.manufacture_id = mn.id " +
                "WHERE  pr.name LIKE '%%' order by pr.price limit 0, 5;";
        ProductFacetQueryData productFormBean = new ProductFacetQueryData();
        productFormBean.setCurrentPage("1");
        productFormBean.setNumberItems("5");
        productFormBean.setProductManufactures(new ArrayList<String>());
        productFormBean.setProductTypes(new ArrayList<String>());
        productFormBean.setOrder(ProductOrderConst.PRICE_HEIGHT_LOW);
        PrepareStatementBuilderParamsBean generate = paramGenerator.generate(productFormBean);
        String actualQuery = generate.getSqlQuery();
        Assert.assertEquals(expectedSqlQuery,actualQuery);
    }

    @Test
    public void testShouldReturnCorrectSQLQueryWhenPutProductFormBeanWithCurrentPageAndNegativeNumberItemsAndOrderParameters(){
        String expectedSqlQuery = "SELECT *, pr.id as pr_id FROM shop.product as pr " +
                "inner join shop.product_type as prt on pr.product_type_id = prt.id " +
                "inner join shop.manufacturer as mn on pr.manufacture_id = mn.id " +
                "WHERE  pr.name LIKE '%%' order by pr.price limit 0, 5;";
        ProductFacetQueryData productFormBean = new ProductFacetQueryData();
        productFormBean.setCurrentPage("1");
        productFormBean.setNumberItems("-5");
        productFormBean.setProductManufactures(new ArrayList<String>());
        productFormBean.setProductTypes(new ArrayList<String>());
        productFormBean.setOrder(ProductOrderConst.PRICE_HEIGHT_LOW);
        PrepareStatementBuilderParamsBean generate = paramGenerator.generate(productFormBean);
        String actualQuery = generate.getSqlQuery();
        Assert.assertEquals(expectedSqlQuery,actualQuery);
    }

    @Test
    public void testShouldReturnCorrectSQLQueryWhenPutProductFormBeanWithCurrentPageAndNumberItemsAndNegativeOrderParameters(){
        String expectedSqlQuery = "SELECT *, pr.id as pr_id FROM shop.product as pr " +
                "inner join shop.product_type as prt on pr.product_type_id = prt.id " +
                "inner join shop.manufacturer as mn on pr.manufacture_id = mn.id " +
                "WHERE  pr.name LIKE '%%' order by pr.price limit 0, 5;";
        ProductFacetQueryData productFormBean = new ProductFacetQueryData();
        productFormBean.setCurrentPage("-1");
        productFormBean.setNumberItems("5");
        productFormBean.setProductManufactures(new ArrayList<String>());
        productFormBean.setProductTypes(new ArrayList<String>());
        productFormBean.setOrder(ProductOrderConst.PRICE_HEIGHT_LOW);
        PrepareStatementBuilderParamsBean generate = paramGenerator.generate(productFormBean);
        String actualQuery = generate.getSqlQuery();
        Assert.assertEquals(expectedSqlQuery,actualQuery);
    }
}
