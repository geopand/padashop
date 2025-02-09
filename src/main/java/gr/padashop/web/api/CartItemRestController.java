package gr.padashop.web.api;

import gr.padashop.models.CartItem;
import gr.padashop.repositories.CartItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api/cart")
public class CartItemRestController {
    private static final Logger logger = LoggerFactory.getLogger(CartItemRestController.class);

    private final CartItemRepository cartItemRepository;

    public CartItemRestController(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }


    @GetMapping("/items/{userId}")
    List<CartItem> getAllByUserId(@PathVariable long userId) {
        return cartItemRepository.getAllByUserId(userId);
    }

    @PutMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam long userId, @RequestParam long productId) {

        List<CartItem> itemsIncart = cartItemRepository.getAllByUserIdAndProduct(userId, productId);
        if (itemsIncart.isEmpty()) {
            CartItem item = new CartItem(userId, productId);
            cartItemRepository.create(item);
            return ResponseEntity.ok().build();
        }

        int rowsAffected = cartItemRepository.incrementQuantityByUserAndProduct(userId, productId);
        if (rowsAffected == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user id or product id are not found");
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/remove")
    public ResponseEntity<String> removeFromCart(@RequestParam long userId, @RequestParam long productId) {
        int rowsAffected = cartItemRepository.decrementQuantityByUserAndProduct(userId, productId);
        if (rowsAffected == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user id or product id are not found");
        }
        return ResponseEntity.ok().build();
    }

}
