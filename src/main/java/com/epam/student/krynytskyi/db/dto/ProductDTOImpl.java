package com.epam.student.krynytskyi.db.dto;

import com.epam.student.krynytskyi.entity.Manufacture;
import com.epam.student.krynytskyi.entity.Product;
import com.epam.student.krynytskyi.entity.ProductType;

import java.sql.ResultSet;

/**
 * Created by Anton_Krynytskyi on 5/8/2015.
 */
public class ProductDTOImpl implements ProductDTO {
    @Override
    public Product extract(ResultSet resSet) throws Exception {
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
}
