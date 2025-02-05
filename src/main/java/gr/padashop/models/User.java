package gr.padashop.models;

import java.util.Objects;

public class User {

    private Long id;
    private String fName;
    private String lName;
    private String email;
    private String country;
    private String strAddress;
    private String city;
    private String region;
    private String zipCode;


    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStrAddress() {
        return strAddress;
    }

    public void setStrAddress(String strAddress) {
        this.strAddress = strAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fname='" + fName + '\'' +
                ", lname='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", strAddress='" + strAddress + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(fName, user.fName) && Objects.equals(lName, user.lName) && Objects.equals(email, user.email) && Objects.equals(country, user.country) && Objects.equals(strAddress, user.strAddress) && Objects.equals(city, user.city) && Objects.equals(region, user.region) && Objects.equals(zipCode, user.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fName, lName, email, country, strAddress, city, region, zipCode);
    }
}
