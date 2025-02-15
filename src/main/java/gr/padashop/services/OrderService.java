package gr.padashop.services;

import gr.padashop.models.*;
import gr.padashop.repositories.CartItemRepository;
import gr.padashop.repositories.OrderItemRepository;
import gr.padashop.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository,
                        CartItemRepository cartItemRepository,
                        OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderItemRepository = orderItemRepository;
    }


    Map<String, Integer> CREDIT_CARD_NAMES_TO_IDS = new HashMap<>() {
        {
            put("Mastercard", 1);
            put("VISA", 2);
            put("American Express", 3);
        }
    };

    public Long createOrder(OrderDto orderDto) {

        List<CartItem> cartItems = cartItemRepository.getAllByUserId(orderDto.getUserId());
        int creditCardType = CREDIT_CARD_NAMES_TO_IDS.get(orderDto.getCreditCard().cardType());

        Order order = new Order();
        order.setUserId((long) orderDto.getUserId());
        order.setCcCVC(orderDto.getCreditCard().cvc());
        order.setCcName(orderDto.getCreditCard().owner());
        order.setCcNumber(orderDto.getCreditCard().number());
        order.setCcExpiryMoth(orderDto.getCreditCard().expiryMonth());
        order.setCcExpiryYear(orderDto.getCreditCard().expiryYear());
        order.setCcType(new CCType((long)creditCardType));
        order.setCity(orderDto.getAddress().city());
        order.setCountry(orderDto.getAddress().country());
        order.setStreet(orderDto.getAddress().street());
        order.setState(orderDto.getAddress().state());
        order.setZipCode(orderDto.getAddress().zipCode());

        long orderId;
        try {
            System.out.println("Before order id creation");

            orderId = orderRepository.createAndGetId(order).longValue();
            System.out.println("AFter order id creation");
            cartItems.forEach(cartItem -> {
                orderItemRepository.createWithOrderId(new OrderItem(orderId,
                        cartItem.getProduct(),
                        cartItem.getQuantity(),
                        cartItem.getProduct().getPrice()));
            });

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return orderId;
    }


    public Order retrieveOrderById(Long id) {
        List<OrderItem> items = orderItemRepository.getAllByOrderId(id);
        Optional<Order> order = orderRepository.getById(id);
        order.ifPresent(o -> o.setItems(items));

        return order.orElse(null);
    }
}
