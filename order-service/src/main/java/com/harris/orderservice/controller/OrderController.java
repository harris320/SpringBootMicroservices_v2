package com.harris.orderservice.controller;

import com.harris.orderservice.dto.OrderRequest;
import com.harris.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        Boolean isOrderPlaced = orderService.placeOrder(orderRequest);
        return isOrderPlaced ? "Order Placed Successfully" : "Product out of stock. Please try later";
    }
}
