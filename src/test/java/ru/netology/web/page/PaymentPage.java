package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {

    private SelenideElement cardNumber = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement cardMonth = $("[placeholder='08']");
    private SelenideElement cardYear = $("[placeholder='22']");
    private SelenideElement cardOwner = $("fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input");
    private SelenideElement cardCVC = $("[placeholder='999']");
    private SelenideElement buyButton = $$(".button").find(exactText("Продолжить"));

    private SelenideElement sucсessNotification = $(withText("Успешно"));
    private SelenideElement errorNotification = $(withText("Ошибка"));
    private SelenideElement cardNumberFieldWarning = $("fieldset > div:nth-child(1) > span > span > span.input__sub");
    private SelenideElement monthFieldWarning = $("div:nth-child(2) > span > span:nth-child(1) > span > span > span.input__sub");
    private SelenideElement yearFieldWarning = $("div:nth-child(2) > span > span:nth-child(2) > span > span > span.input__sub");
    private SelenideElement ownerFieldWarning = $("div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__sub");
    private SelenideElement cvcFieldWarning = $("div:nth-child(3) > span > span:nth-child(2) > span > span > span.input__sub");

    public void enterCardInfo(DataHelper.CardInfo cardInfo) {
        cardNumber.setValue(cardInfo.getCardNumber());
        cardMonth.setValue(cardInfo.getMonth());
        cardYear.setValue(cardInfo.getYear());
        cardOwner.setValue(cardInfo.getOwner());
        cardCVC.setValue(cardInfo.getCvc());
        buyButton.click();
    }

    public void successNotification() {
        sucсessNotification.waitUntil(Condition.visible, 15000);
    }

    public void errorNotification() {
        errorNotification.waitUntil(Condition.visible, 15000);
    }

    public void invalidCardNumber() {
        cardNumberFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void invalidMonth() {
        monthFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void invalidYear() {
        yearFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void invalidOwner() {
        ownerFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void invalidCvc() {
        cvcFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void emptyCardNumber() {
        cardNumberFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void emptyMonth() {
        monthFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void emptyYear() {
        yearFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void emptyOwner() {
        ownerFieldWarning.shouldHave(text("Поле обязательно для заполнения"));
    }

    public void emptyCvc() {
        cvcFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void expiredYear() {
        yearFieldWarning.shouldHave(text("Истёк срок действия карты"));
    }

    public void expiredMonth() {
        monthFieldWarning.shouldHave(text("Неверно указан срок действия карты"));
    }

    public void invalidExpirationDate() {
        yearFieldWarning.shouldHave(text("Неверно указан срок действия карты"));
    }
}

