package com.bgjshop.backend.controller;

import com.bgjshop.backend.dto.OrderDto;
import com.bgjshop.backend.dto.OrderItemDto;
import com.bgjshop.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> get(@PathVariable Long id) {
        OrderDto order = orderService.get(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @GetMapping(path = "/recent", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDto> getRecent() {
        return orderService.getRecent();
    }

    @GetMapping(path = "/getOrdersForUser/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDto> getOrdersForUser(@PathVariable String userId) {
        return orderService.getOrdersForUser(userId);
    }

    @GetMapping(path = "/getGamesForOrder/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderItemDto> getGamesForOrder(@PathVariable Long orderId) {
        return orderService.getGamesForOrder(orderId);
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> create(@Valid @RequestBody OrderDto request) {
        orderService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody OrderDto request) {
        orderService.update(request);
    }

    @PutMapping(path = "/updateStatus", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateStatus(@Valid @RequestBody OrderDto request) {
        orderService.updateStatus(request);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }
}
