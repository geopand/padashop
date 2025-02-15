package gr.padashop.web.api;


import gr.padashop.models.Order;
import gr.padashop.models.OrderDto;
import gr.padashop.repositories.CartItemRepository;
import gr.padashop.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final CartItemRepository cartItemRepository;
    private final OrderService orderService;


    public OrderController(CartItemRepository cartItemRepository, OrderService orderService) {
        this.cartItemRepository = cartItemRepository;
        this.orderService = orderService;

    }

    @PostMapping
    public OrderId createOrder(@RequestBody OrderDto order) {

        Long createdOrderId = orderService.createOrder(order);
        cartItemRepository.clearCart(order.getUserId());
        return new OrderId(createdOrderId);
    }


    @GetMapping("/{orderId}")
    Order getOrderById(@PathVariable Long orderId) {
        return orderService.retrieveOrderById(orderId);
    }

    static class OrderId {
        Long id;
        public OrderId(Long id){
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }


}
