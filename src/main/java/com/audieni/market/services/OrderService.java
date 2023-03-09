package com.audieni.market.services;

import com.audieni.market.models.Order;
import com.audieni.market.models.OrderProduct;
import com.audieni.market.repositories.OrderProductRepository;
import com.audieni.market.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
    }

    public Optional<List<Order>> findByUserId(int userId) {
        return orderRepository.findByUserId(userId);
    }

    public Optional<List<OrderProduct>> findByOrderId(int orderId) {
        return orderProductRepository.findByOrderId(orderId);
    }

    public Order save(Order order) {
        orderProductRepository.saveAll(order.getOrderProduct());
        return orderRepository.save(order);
    }
}
