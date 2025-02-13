package gr.padashop.services;

import gr.padashop.models.ShippingAddress;
import gr.padashop.models.CartItem;
import gr.padashop.models.Order;
import gr.padashop.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public Order createOrder(List<CartItem> cartItems, ShippingAddress address) {
        return null;
        //todo
    }
}
