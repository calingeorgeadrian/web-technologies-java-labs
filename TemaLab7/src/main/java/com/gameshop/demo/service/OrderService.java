package com.gameshop.demo.service;

import com.gameshop.demo.domain.Order;
import com.gameshop.demo.dto.OrderDto;
import com.gameshop.demo.mapper.OrderMapper;
import com.gameshop.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }
    public OrderDto get(String id) {
        return orderMapper.mapToDto(orderRepository.get(id));
    }

    public List<OrderDto> getAll() {
        return orderRepository.getAll().stream()
                .map(orderMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public OrderDto create(OrderDto request) {
        Order savedOrder = orderRepository.save(orderMapper.mapToEntity(request));
        return orderMapper.mapToDto(savedOrder);
    }

    public OrderDto update(OrderDto request) {
        Order updatedOrder = orderRepository.update(orderMapper.mapToEntity(request));
        if (updatedOrder != null) {
            return orderMapper.mapToDto(updatedOrder);
        }
        return null;
    }

    public String delete(String id) {
        return orderRepository.delete(id);
    }
}
