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

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> create(@Valid @RequestBody OrderDto request) {
        return new ResponseEntity<>(orderService.create(request), HttpStatus.CREATED);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> update(@RequestBody OrderDto request) {
        return new ResponseEntity<>(orderService.update(request), orderService.update(request) != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/delete/{id}")
    public String delete(@PathVariable String id) {
        return orderService.delete(id);
    }
}