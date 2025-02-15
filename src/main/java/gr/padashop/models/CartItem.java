package gr.padashop.models;

public class CartItem {
    private Long id;
    private Long userId;
    private Product product;
    private int quantity;

    public CartItem() {
    }


    public CartItem(Long userId, Long productId) {
        this.userId = userId;
        Product p = new Product();
        p.setId(productId);
        this.product = p;
        this.quantity = 1;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
