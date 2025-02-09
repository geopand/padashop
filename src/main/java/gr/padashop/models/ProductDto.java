package gr.padashop.models;

import java.math.BigDecimal;


//This is oly used by the spring mvc jte form
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private String slug;
    private Long category;
    private String picture;
    private BigDecimal price;
    private String status;
    private Long stock;
    private String brand;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSlug() {
        return slug;
    }

    public Long getCategory() {
        return category;
    }

    public String getPicture() {
        return picture;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public Long getStock() {
        return stock;
    }

    public String getBrand() {
        return brand;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
