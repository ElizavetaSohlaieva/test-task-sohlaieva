package rozetka;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import org.example.dto.Product;
import org.example.pages.MobilePage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ScreenShooterExtension.class)
class RozetkaTest {

    private static MobilePage mobilePage;

    @BeforeAll
    static void setUp() {
        mobilePage = new MobilePage().openPage();;
    }

    private static void assertValidPhones(List<Product> phones) {
        for (Product phone : phones) {
            assertFalse(phone.name().isBlank(), "Phone without a name: " + phone);
            assertTrue(phone.price() > 0, "Phone without a price: " + phone);
        }
    }

    @Test
    void collectPhones() {
        List<Product> phones = mobilePage.getAllPhones();
        phones.forEach(System.out::println);
        assertFalse(phones.isEmpty(), "No phones found on the page");
        assertValidPhones(phones);
    }

    @Test
    void collectTopSalesPhones() {
        List<Product> topSales = mobilePage.getTopSalesPhones();
        topSales.forEach(System.out::println);
        assertFalse(topSales.isEmpty(),
                "No phones found with label 'Top Sales'");
        assertValidPhones(topSales);
    }

    @AfterAll
    static void tearDown() {
        Selenide.closeWebDriver();
    }
}