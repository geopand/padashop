package gr.padashop.models;

import java.math.BigDecimal;import java.util.Objects;

public class Product {

    private Long id;
    private String name;
    private String description;
    private String slug;
    private Category category;
    private String picture;
    private BigDecimal price;
    private String status;
    private Long stock;
    private String brand;

    //TODO characteristics?

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(slug, product.slug) && Objects.equals(category, product.category) && Objects.equals(picture, product.picture) && Objects.equals(price, product.price) && Objects.equals(status, product.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, slug, category, picture, price, status);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", slug='" + slug + '\'' +
                ", category=" + category +
                ", picture='" + picture + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", stock=" + stock +
                ", brand='" + brand + '\'' +
                '}';
    }

    public static Product copy(ProductDto dto) {
        Product p = new Product();
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setBrand(dto.getBrand());
        p.setStock(dto.getStock());
        p.setPicture(dto.getPicture());
        p.setSlug(dto.getSlug());
        p.setCategory(new Category(dto.getCategory()));
        p.setPrice(dto.getPrice());
        return p;
    }
}
