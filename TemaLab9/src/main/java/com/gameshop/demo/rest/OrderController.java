package com.gameshop.demo.rest;

import com.gameshop.demo.dto.OrderDto;
import com.gameshop.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
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

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> create(@Valid @RequestBody OrderDto request) {
        orderService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable Long id, @RequestBody String address) {
        orderService.update(id, address);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }
}