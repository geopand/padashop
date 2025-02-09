package gr.padashop.web.api;

import gr.padashop.models.Cart;
import gr.padashop.repositories.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api/cart")
public class CartRestController {
    private static final Logger logger = LoggerFactory.getLogger(CartRestController.class);

    private final CartRepository cartRepository;

    public CartRestController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    @GetMapping("/{userId}")
    List<Cart> getAllByUserId(@PathVariable long userId) {
        return cartRepository.getAllByUserId(userId);
    }

    @PutMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam long userId, @RequestParam long productId) {

        int rowsAffected = cartRepository.incrementQuantityByUserAndProduct(userId, productId);
        if (rowsAffected == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user id or product id are not found");
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/remove")
    public ResponseEntity<String> removeFromCart(@RequestParam long userId, @RequestParam long productId) {
        int rowsAffected = cartRepository.decrementQuantityByUserAndProduct(userId, productId);
        if (rowsAffected == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user id or product id are not found");
        }
        return ResponseEntity.ok().build();
    }

}
