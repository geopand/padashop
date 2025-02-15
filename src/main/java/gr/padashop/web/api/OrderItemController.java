package gr.padashop.web.api;


import gr.padashop.models.OrderItem;
import gr.padashop.repositories.OrderItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderitems")
public class OrderItemController {
    private static final Logger logger = LoggerFactory.getLogger(OrderItemController.class);

    private final OrderItemRepository orderItemRepository;

    OrderItemController(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;

    }

    @GetMapping("/{orderId}")
    public List<OrderItem> getOrderItemsOfOrder(@PathVariable long orderId) {

        return orderItemRepository.getAllByOrderId(orderId);

    }


}
