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


    OrderController(CartItemRepository cartItemRepository, OrderService orderService) {
        this.cartItemRepository = cartItemRepository;
        this.orderService = orderService;

    }

    @PostMapping
    public Order createOrder(@RequestBody OrderDto order) {
        logger.info("{}", order);
        logger.info("{}", order.getCreditCard().toString());
        logger.info("{}", order.getAddress().toString());

        Long createdOrderId = orderService.createOrder(order);

        return new Order();
    }


    @GetMapping("/{orderId}")
    Order getOrderById(@PathVariable Long orderId) {
        return orderService.retrieveOrderById(orderId);
    }


}
