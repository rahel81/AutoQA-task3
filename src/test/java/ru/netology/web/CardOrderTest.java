package ru.netology.web;


import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {

    @Test
    void shouldSendValidValue() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Елена");
        $("[data-test-id=phone] input").setValue("+79100408050");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".Success_successBlock__2L3Cw").shouldHave(text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSendInvalidName() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Elena");
        $("[data-test-id=phone] input").setValue("+79100408050");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave
                (text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldSendInvalidPhone() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Елена");
        $("[data-test-id=phone] input").setValue("+7910040805");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave
                (text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldSendInvalidCheckbox() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Елена");
        $("[data-test-id=phone] input").setValue("+79100408050");
        //$("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=agreement].input_invalid").shouldHave
                (text("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    void shouldSendEmptyField() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("");
        //$("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave
                (text("Поле обязательно для заполнения"));
    }
}
