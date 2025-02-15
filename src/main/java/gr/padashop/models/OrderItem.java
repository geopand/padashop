package gr.padashop.models;

import java.math.BigDecimal;


public class OrderItem {
    private Long id;
    private Long orderId;
    private Product product;
    private Integer quantity;
    private BigDecimal price;
    private String itemStatus;
    private Long shippingDate; //epoch


    public OrderItem(){}

    public OrderItem(Long orderId, Product product, Integer quantity, BigDecimal price) {
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Long getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Long shippingDate) {
        this.shippingDate = shippingDate;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                ", itemStatus='" + itemStatus + '\'' +
                ", shippingDate=" + shippingDate +
                '}';
    }
}
