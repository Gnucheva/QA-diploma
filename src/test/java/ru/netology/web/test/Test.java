package ru.netology.web.test;

import com.codeborne.selenide.Selenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.sql.DbHelper;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test {

    DashboardPage dashboardPage = new DashboardPage();

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @AfterAll
    static void cleanDatabase() {
        DbHelper.cleanDb();
    }

    @org.junit.jupiter.api.Test
    void shouldPayByApprovedDebitCard() {
        val paymentPage = dashboardPage.payByDebitCard();
        val approvedCardInformation = DataHelper.getApprovedCardInformation();
        paymentPage.enterCardInfo(approvedCardInformation);
        paymentPage.successNotification();
        val paymentStatus = DbHelper.getPaymentEntity();
        assertEquals("APPROVED", paymentStatus);
    }

    @org.junit.jupiter.api.Test
    void shouldPayByDeclinedDebitCard() {
        val paymentPage = dashboardPage.payByDebitCard();
        val declinedCardInformation = DataHelper.getDeclinedCardInformation();
        paymentPage.enterCardInfo(declinedCardInformation);
        paymentPage.errorNotification();
        val paymentStatus = DbHelper.getPaymentEntity();
        assertEquals("DECLINED", paymentStatus);
    }

    @org.junit.jupiter.api.Test
    void shouldPayByApprovedCreditCard() {
        val paymentPage = dashboardPage.payByCreditCard();
        val approvedCardInformation = DataHelper.getApprovedCardInformation();
        paymentPage.enterCardInfo(approvedCardInformation);
        paymentPage.successNotification();
        val creditStatus = DbHelper.getCreditEntity();
        assertEquals("APPROVED", creditStatus);
    }

    @org.junit.jupiter.api.Test
    void shouldPayByDeclinedCreditCard() {
        val paymentPage = dashboardPage.payByCreditCard();
        val declinedCardInformation = DataHelper.getDeclinedCardInformation();
        paymentPage.enterCardInfo(declinedCardInformation);
        paymentPage.errorNotification();
        val creditStatus = DbHelper.getCreditEntity();
        assertEquals("DECLINED", creditStatus);
    }

    @org.junit.jupiter.api.Test
    void shouldPayByDebitCardWithInvalidNumber() {
        val paymentPage = dashboardPage.payByDebitCard();
        val invalidCardInformation = DataHelper.getInvalidCardInformation();
        paymentPage.enterCardInfo(invalidCardInformation);
        paymentPage.invalidCardNumber();
    }

    @org.junit.jupiter.api.Test
    void shouldPayByCreditCardWithInvalidNumber() {
        val paymentPage = dashboardPage.payByCreditCard();
        val invalidCardInformation = DataHelper.getInvalidCardInformation();
        paymentPage.enterCardInfo(invalidCardInformation);
        paymentPage.invalidCardNumber();
    }

    @org.junit.jupiter.api.Test
    void shouldPayByDebitCardWithExpiredYear() {
        val paymentPage = dashboardPage.payByDebitCard();
        val expiredYearCardInformation = DataHelper.getExpiredYearCardInformation();
        paymentPage.enterCardInfo(expiredYearCardInformation);
        paymentPage.expiredYear();
    }

    @org.junit.jupiter.api.Test
    void shouldPayByCreditCardWithExpiredYear() {
        val paymentPage = dashboardPage.payByCreditCard();
        val expiredYearCardInformation = DataHelper.getExpiredYearCardInformation();
        paymentPage.enterCardInfo(expiredYearCardInformation);
        paymentPage.expiredYear();
    }

    @org.junit.jupiter.api.Test
    void shouldPayByDebitCardWithInvalidExpirationDate() {
        val paymentPage = dashboardPage.payByDebitCard();
        val invalidExpirationDate = DataHelper.getInvalidExpirationDateCardInformation();
        paymentPage.enterCardInfo(invalidExpirationDate);
        paymentPage.invalidExpirationDate();
    }

    @org.junit.jupiter.api.Test
    void shouldPayByCreditCardWithInvalidExpirationDate() {
        val paymentPage = dashboardPage.payByCreditCard();
        val invalidExpirationDate = DataHelper.getInvalidExpirationDateCardInformation();
        paymentPage.enterCardInfo(invalidExpirationDate);
        paymentPage.invalidExpirationDate();
    }

    @org.junit.jupiter.api.Test
    void shouldPayByDebitCardWithExpiredMonth() {
        val paymentPage = dashboardPage.payByDebitCard();
        val expiredMonth = DataHelper.getExpiredMonthCardInformation();
        paymentPage.enterCardInfo(expiredMonth);
        paymentPage.expiredMonth();
    }

    @org.junit.jupiter.api.Test
    void shouldPayByCreditCardWithExpiredMonth() {
        val paymentPage = dashboardPage.payByCreditCard();
        val expiredMonth = DataHelper.getExpiredMonthCardInformation();
        paymentPage.enterCardInfo(expiredMonth);
        paymentPage.expiredMonth();
    }

    @org.junit.jupiter.api.Test
    void shouldPayByDebitCardWithEmptyCardInformation() {
        val paymentPage = dashboardPage.payByDebitCard();
        val emptyCardInformation = DataHelper.getEmptyCardInformation();
        paymentPage.enterCardInfo(emptyCardInformation);
        paymentPage.emptyCardNumber();
        paymentPage.emptyMonth();
        paymentPage.emptyYear();
        paymentPage.emptyOwner();
        paymentPage.emptyCvc();
    }

    @org.junit.jupiter.api.Test
    void shouldPayByCreditCardWithEmptyCardInformation() {
        val paymentPage = dashboardPage.payByCreditCard();
        val emptyCardInformation = DataHelper.getEmptyCardInformation();
        paymentPage.enterCardInfo(emptyCardInformation);
        paymentPage.emptyCardNumber();
        paymentPage.emptyMonth();
        paymentPage.emptyYear();
        paymentPage.emptyOwner();
        paymentPage.emptyCvc();
    }

    @org.junit.jupiter.api.Test
    void shouldPayByDebitCardWithInvalidOwner() {
        val paymentPage = dashboardPage.payByDebitCard();
        val invalidOwner = DataHelper.getInvalidOwnerCard();
        paymentPage.enterCardInfo(invalidOwner);
        paymentPage.invalidOwner();
    }

    @org.junit.jupiter.api.Test
    void shouldPayByCreditCardWithInvalidOwner() {
        val paymentPage = dashboardPage.payByCreditCard();
        val invalidOwner = DataHelper.getInvalidOwnerCard();
        paymentPage.enterCardInfo(invalidOwner);
        paymentPage.invalidOwner();
    }

    @org.junit.jupiter.api.Test
    void shouldPayByDebitCardWithValidCardNumberAndInvalidOtherFields() {
        val paymentPage = dashboardPage.payByDebitCard();
        val validCardNumberWithInvalidOtherFields = DataHelper.getValidCardNumberWithInvalidOtherFields();
        paymentPage.enterCardInfo(validCardNumberWithInvalidOtherFields);
        paymentPage.invalidMonth();
        paymentPage.invalidYear();
        paymentPage.invalidOwner();
        paymentPage.invalidCvc();
    }

    @org.junit.jupiter.api.Test
    void shouldPayByCreditCardWithValidCardNumberAndInvalidOtherFields() {
        val paymentPage = dashboardPage.payByCreditCard();
        val validCardNumberWithInvalidOtherFields = DataHelper.getValidCardNumberWithInvalidOtherFields();
        paymentPage.enterCardInfo(validCardNumberWithInvalidOtherFields);
        paymentPage.invalidMonth();
        paymentPage.invalidYear();
        paymentPage.invalidOwner();
        paymentPage.invalidCvc();
    }

}
