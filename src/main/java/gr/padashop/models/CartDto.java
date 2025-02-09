package gr.padashop.models;

import java.util.Objects;

public class CartDto {
    private Long userId;
    private Long productId;
    private int quantity;

    public CartDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CartDto cart = (CartDto) o;
        return quantity == cart.quantity && Objects.equals(userId, cart.userId) && Objects.equals(productId, cart.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, productId, quantity);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
