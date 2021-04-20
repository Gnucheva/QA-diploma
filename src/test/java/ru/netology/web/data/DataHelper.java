package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    static DateGenerator dateGenerator = new DateGenerator();

    public static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getInvalidCardNumber() {
        return "4444 4444 4444 444";
    }

    public static String getValidOwner() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().fullName();
    }

    public static String getInvalidOwner() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String getValidCvc() {
        return "111";
    }

    public static String getInvalidCvc() {
        return "11";
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String year;
        String month;
        String owner;
        String cvc;
    }

    public static CardInfo getApprovedCardInformation() {
        return new CardInfo(getApprovedCardNumber(), dateGenerator.getValidExpirationDate().getYear(), dateGenerator.getValidMonth().getMonth(), getValidOwner(), getValidCvc());
    }

    public static CardInfo getDeclinedCardInformation() {
        return new CardInfo(getDeclinedCardNumber(), dateGenerator.getValidExpirationDate().getYear(), dateGenerator.getValidMonth().getMonth(), getValidOwner(), getValidCvc());
    }

    public static CardInfo getInvalidCardInformation() {
        return new CardInfo(getInvalidCardNumber(), dateGenerator.getValidExpirationDate().getYear(), dateGenerator.getValidMonth().getMonth(), getValidOwner(), getValidCvc());
    }

    public static CardInfo getExpiredMonthCardInformation() {
        return new CardInfo(getApprovedCardNumber(), dateGenerator.getCurrentYear().getYear(), dateGenerator.getExpiredMonth().getMonth(), getValidOwner(), getValidCvc());
    }

    public static CardInfo getExpiredYearCardInformation() {
        return new CardInfo(getApprovedCardNumber(), dateGenerator.getExpiredYear().getYear(), dateGenerator.getValidMonth().getMonth(), getValidOwner(), getValidCvc());
    }

    public static CardInfo getInvalidExpirationDateCardInformation() {
        return new CardInfo(getApprovedCardNumber(), dateGenerator.getInvalidExpirationDate().getYear(), dateGenerator.getValidMonth().getMonth(), getValidOwner(), getValidCvc());
    }

    public static CardInfo getEmptyCardInformation() {
        return new CardInfo(" ", " ", " ", " ", " ");
    }

    public static CardInfo getValidCardNumberWithInvalidOtherFields() {
        return new CardInfo(getApprovedCardNumber(), dateGenerator.getInvalidYear().getYear(), dateGenerator.getInvalidMonth().getMonth(), getInvalidOwner(), getInvalidCvc());
    }

    public static CardInfo getInvalidOwnerCard() {
        return new CardInfo(getApprovedCardNumber(),
                dateGenerator.getValidExpirationDate().getYear(),
                dateGenerator.getValidMonth().getMonth(),
                getInvalidOwner(),
                getValidCvc());
    }

}

