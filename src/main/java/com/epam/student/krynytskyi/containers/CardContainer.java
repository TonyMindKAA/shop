package com.epam.student.krynytskyi.containers;

import com.epam.student.krynytskyi.beans.card.CardItem;
import com.epam.student.krynytskyi.beans.card.ProductFixedPrice;
import com.epam.student.krynytskyi.entity.Product;

import java.util.*;

public class CardContainer {
    private Map<ProductFixedPrice, Integer> card = new HashMap<>();

    public void add(Product product, int numbers) {
        ProductFixedPrice immutableProduct = new ProductFixedPrice(product);
        if (card.containsKey(immutableProduct)) {
            int currentNumbers = card.get(immutableProduct);
            int resultNumbers = numbers + currentNumbers;
            card.put(immutableProduct, resultNumbers);
        } else
            card.put(immutableProduct, numbers);
    }

    public void remove(Product product) {
        card.remove(product);
    }

    public int size() {
        int cardSize = 0;
        for (Map.Entry<ProductFixedPrice, Integer> entry : card.entrySet()) {
            cardSize += entry.getValue();
        }
        return cardSize;
    }

    public List<CardItem> getAll() {
        List<CardItem> cardItems = new ArrayList<>();
        for (Map.Entry<ProductFixedPrice, Integer> entry : card.entrySet()) {
            cardItems.add(new CardItem(entry.getKey(), entry.getValue()));
        }
        return cardItems;
    }

    public long calculationPurchases() {
        int totalProducts = 0;
        for (Map.Entry<ProductFixedPrice, Integer> entry : card.entrySet()) {
            ProductFixedPrice product = entry.getKey();
            Integer itemNumbers = entry.getValue();
            totalProducts += product.getPrice() * itemNumbers;
        }
        return totalProducts;
    }
}
