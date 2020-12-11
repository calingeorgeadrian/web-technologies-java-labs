package com.gameshop.demo.service;

import com.gameshop.demo.dto.OrderDto;
import com.gameshop.demo.mapper.OrderMapper;
import com.gameshop.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }
    public OrderDto get(Long id) {
        return orderRepository.get(id);
    }

    public List<OrderDto> getAll() {
        return orderRepository.getAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(OrderDto order) {
        orderRepository.save(order);
    }

    public void update(Long id, String address) {
        orderRepository.update(id, address);
    }

    public void delete(Long id) {
        orderRepository.delete(id);
    }
}
