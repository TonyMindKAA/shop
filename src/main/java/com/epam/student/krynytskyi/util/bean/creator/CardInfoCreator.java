package com.epam.student.krynytskyi.util.bean.creator;

import com.epam.student.krynytskyi.beans.card.CardInfo;
import com.epam.student.krynytskyi.containers.CardContainer;

public class CardInfoCreator {

    public CardInfo create(CardContainer card) {
        CardInfo cardInfo = new CardInfo();
        cardInfo.setProductsNumber(card.size());
        cardInfo.setTotalCost(card.calculationPurchases());
        return cardInfo;
    }
}
