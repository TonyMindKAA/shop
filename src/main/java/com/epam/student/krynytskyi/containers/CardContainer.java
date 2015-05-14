package com.epam.student.krynytskyi.containers;

import com.epam.student.krynytskyi.beans.card.CardItem;
import com.epam.student.krynytskyi.beans.card.ProductFixedPrice;
import com.epam.student.krynytskyi.entity.Product;

import java.util.*;

public class CardContainer {
    private Map<ProductFixedPrice, Integer> card = new HashMap<>();

    public void add(Product product, int numbers) {
        card.put(new ProductFixedPrice(product), numbers);
    }

    public void remove(Product product) {
        card.remove(product);
    }

    public int size() {
        return card.size();
    }

    public List<CardItem> getAll() {
        List<CardItem> cardItems = new ArrayList<>();
        for (Map.Entry<ProductFixedPrice, Integer> entry : card.entrySet()) {
            cardItems.add(new CardItem(entry.getKey(),entry.getValue()));
        }
        return cardItems;
    }

    public long calculationPurchases() {
        int totalProducts = 0;
        for (Map.Entry<ProductFixedPrice, Integer> entry : card.entrySet()) {
            ProductFixedPrice product = entry.getKey();
            Integer itemNumbers = entry.getValue();
            totalProducts += product.getPrice()*itemNumbers;
        }
        return totalProducts;
    }
}
