import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
public class NegativeTestsForCard {
    public static String setLocalDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy",
                new Locale("ru")));
    }

    @Test
    void ShouldDataIncorrect() {
        String date = setLocalDate(2);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue("Усаева Элина");
        $("[data-test-id=phone] input").setValue("+79996705891");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Заказ на выбранную дату невозможен")).shouldBe(visible);
    }
    @Test
    void ShouldDataIncorrect2() {
        String date = setLocalDate(0);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue("Усаева Элина");
        $("[data-test-id=phone] input").setValue("+79996705891");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Заказ на выбранную дату невозможен")).shouldBe(visible);
    }
    @Test
    void ShouldCityIncorrect() {
        String date = setLocalDate(3);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Альметьевск");
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue("Усаева Элина");
        $("[data-test-id=phone] input").setValue("+79996705891");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Доставка в выбранный город недоступна")).shouldBe(visible);
    }
    @Test
    void ShouldNameIncorrect() {
        String date = setLocalDate(3);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue("Usaeva Elina");
        $("[data-test-id=phone] input").setValue("+79996705891");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(visible);
    }
    @Test
    void ShouldNumberIncorrect() {
        String date = setLocalDate(3);
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue("Усаева Элина");
        $("[data-test-id=phone] input").setValue("+7 (999) 6705891");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(visible);
    }
    @Test
    void ShouldEmpty() {
        open("http://localhost:9999");
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='city'].input_invalid").shouldBe(visible, Duration.ofSeconds(5)).should(exactText("Поле обязательно для заполнения"));
    }
    @Test
    void ShouldCityEmpty() {
        String date = setLocalDate(3);
        open("http://localhost:9999");
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue("Усаева Элина");
        $("[data-test-id=phone] input").setValue("+79996705891");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='city'].input_invalid").shouldBe(visible, Duration.ofSeconds(5)).should(exactText("Поле обязательно для заполнения"));
    }

}
