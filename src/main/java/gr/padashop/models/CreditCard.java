package gr.padashop.models;

public record CreditCard(String owner, String cardType, String number, int expiryMonth, int expiryYear, int cvc) {
}
