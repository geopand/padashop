package gr.padashop.web.api;

import gr.padashop.models.CartItem;
import gr.padashop.repositories.CartItemRepository;
import gr.padashop.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/cart")
public class CartItemRestController {
    private static final Logger logger = LoggerFactory.getLogger(CartItemRestController.class);

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartItemRestController(CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }


    @GetMapping("/items/{userId}")
    List<CartItem> getAllByUserId(@PathVariable long userId) {
        return cartItemRepository.getAllByUserId(userId);
    }

    @PutMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam long userId, @RequestParam long productId) {

        Optional<CartItem> cartItem = cartItemRepository.getCartItemByUserIdAndProduct(userId, productId);
        int stock = productRepository.countById();
        if (stock <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough stock");
        }

        if (cartItem.isEmpty()) {
                CartItem item = new CartItem(userId, productId);
                cartItemRepository.create(item);
        } else {
            if (cartItem.get().getQuantity() >= stock) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough stock");
            } else {
                int rowsAffected = cartItemRepository.incrementQuantityByUserAndProduct(userId, productId);
                if (rowsAffected == 0) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user id or product id are not found");
                }
            }
        }
        return ResponseEntity.ok().build();

    }

    @PutMapping("/remove")
    public ResponseEntity<String> removeFromCart(@RequestParam long userId, @RequestParam long productId) {
        int stock = productRepository.countById();
        if (stock <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough stock");
        }
        Optional<CartItem> cartItem = cartItemRepository.getCartItemByUserIdAndProduct(userId, productId);
        if (cartItem.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not in cart");
        }
        if (cartItem.get().getQuantity() == 1) {
            cartItemRepository.delete(cartItem.get().getId().toString());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item not in cart");
        }

        int rowsAffected = cartItemRepository.decrementQuantityByUserAndProduct(userId, productId);
        if (rowsAffected == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user id or product id are not found");
        }
        return ResponseEntity.ok().build();
    }

}
