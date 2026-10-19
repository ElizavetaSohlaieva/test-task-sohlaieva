package org.example.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.example.dto.Product;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class MobilePage {

    private final ElementsCollection cards = $$("rz-catalog-tile");

    private static final String TITLE = ".tile-title";
    private static final String PRICE = ".price";
    private static final String TOP_SALES_LABEL = ".promo-label_type_popularity";

    public MobilePage openPage() {
        open("/ua/mobile-phones/c80003/");
        return this;
    }

    public List<Product> getAllPhones() {
        cards.shouldHave(sizeGreaterThan(0));
        List<Product> phones = new ArrayList<>();
        for (SelenideElement card : cards) {
            phones.add(toProduct(card));
        }
        return phones;
    }

    private Product toProduct(SelenideElement card) {
        return new Product(
                card.$(TITLE).getText(),
                parsePrice(card.$(PRICE).getText()),
                card.$(TOP_SALES_LABEL).exists());
    }

    private static int parsePrice(String text) {
        return Integer.parseInt(text.replaceAll("\\D", ""));
    }

    public List<Product> getTopSalesPhones() {
        return getAllPhones().stream()
                .filter(Product::topSales)
                .toList();
    }
}