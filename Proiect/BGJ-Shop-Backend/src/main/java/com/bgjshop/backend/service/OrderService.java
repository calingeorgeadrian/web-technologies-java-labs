package com.bgjshop.backend.service;

import com.bgjshop.backend.domain.Order;
import com.bgjshop.backend.domain.OrderItem;
import com.bgjshop.backend.dto.OrderDto;
import com.bgjshop.backend.dto.OrderItemDto;
import com.bgjshop.backend.exceptions.EntityNotFoundException;
import com.bgjshop.backend.mapper.OrderItemMapper;
import com.bgjshop.backend.mapper.OrderMapper;
import com.bgjshop.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final OrderItemMapper orderItemMapper;
    private final CartService cartService;

    @Autowired
    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, OrderItemMapper orderItemMapper, CartService cartService) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.orderItemMapper = orderItemMapper;
        this.cartService = cartService;
    }

    public OrderDto get(Long id) {
        return orderRepository.get(id).orElseThrow(()-> new EntityNotFoundException("Order"));
    }

    public List<OrderDto> getAll() {
        return orderRepository.getAll();
    }

    public List<OrderDto> getRecent() {
        return orderRepository.getRecent();
    }

    public List<OrderDto> getOrdersForUser(String userId) {
        return orderRepository.getOrdersForUser(userId);
    }

    public List<OrderItemDto> getGamesForOrder(Long orderId) {
        return orderRepository.getGamesForOrder(orderId);
    }

    @Transactional(rollbackFor = Exception.class)
    public Long create(OrderDto request) {
        Order order = orderMapper.toEntity(request);
        Long orderId = orderRepository.save(order);
        List<OrderItem> orderItems = orderItemMapper.toEntityList(request.getItems());
        orderRepository.saveItems(orderItems, orderId);
        cartService.empty(request.getUserId());
        return orderId;

    }

    public void update(OrderDto request) {
        Order order = orderMapper.toEntity(request);
        orderRepository.update(order);
    }

    public void updateStatus(OrderDto request) {
        Order order = orderMapper.toEntity(request);
        orderRepository.updateStatus(order);
    }

    public void delete(Long id) {
        orderRepository.delete(id);
    }
}
