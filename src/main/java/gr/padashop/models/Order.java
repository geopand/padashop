package gr.padashop.models;

import java.util.List;

public class Order {

    private Long id;
    private Long userId;
    private List<OrderItem> items;
    private CCType ccType;
    private String ccName;
    private String ccNumber;
    private int ccExpiryMoth;
    private int ccExpiryYear;
    private int ccCVC;
    private boolean isSelfPickUp;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private long createdAt;


    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public CCType getCcType() {
        return ccType;
    }

    public void setCcType(CCType ccType) {
        this.ccType = ccType;
    }

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public int getCcExpiryMoth() {
        return ccExpiryMoth;
    }

    public void setCcExpiryMoth(int ccExpiryMoth) {
        this.ccExpiryMoth = ccExpiryMoth;
    }

    public int getCcExpiryYear() {
        return ccExpiryYear;
    }

    public void setCcExpiryYear(int ccExpiryYear) {
        this.ccExpiryYear = ccExpiryYear;
    }

    public int getCcCVC() {
        return ccCVC;
    }

    public void setCcCVC(int ccCVC) {
        this.ccCVC = ccCVC;
    }

    public boolean isSelfPickUp() {
        return isSelfPickUp;
    }

    public void setSelfPickUp(boolean selfPickUp) {
        isSelfPickUp = selfPickUp;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", userId='" + userId + '\'' + ", items=" + items + ", ccType='" + ccType + '\'' + ", ccName='" + ccName + '\'' + ", ccNumber='" + ccNumber + '\'' + ", ccExpiryMoth=" + ccExpiryMoth + ", ccExpiryYear=" + ccExpiryYear + ", ccCVC=" + ccCVC + ", isSelfPickUp=" + isSelfPickUp + ", street='" + street + '\'' + ", city='" + city + '\'' + ", state='" + state + '\'' + ", zipCode='" + zipCode + '\'' + ", country='" + country + '\'' + '}';
    }
}
