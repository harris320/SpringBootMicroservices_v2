package com.harris.orderservice.service;

import com.harris.orderservice.dto.InventoryResponse;
import com.harris.orderservice.dto.OrderLineItemsDto;
import com.harris.orderservice.dto.OrderRequest;
import com.harris.orderservice.model.Order;
import com.harris.orderservice.model.OrderLineItems;
import com.harris.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    WebClient.Builder webClientBuilder;

    public Boolean placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(orderLineItemsDto -> mapToDto(orderLineItemsDto))
                .collect(Collectors.toList());

        order.setOrderLineItemsList(orderLineItemsList);

        List<String> skuCodes = order.getOrderLineItemsList()
                .stream()
                .map(OrderLineItems::getSku)
                .toList();

        // call inventory service and place order if order is in stock
        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                .uri("http://INVENTORY_SERVICE/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .blockOptional().get();

        boolean allProductsIsInStock = Arrays.stream(inventoryResponses)
                .allMatch(InventoryResponse::getIsInStock);

        if(allProductsIsInStock){
            orderRepository.save(order);
            return true;
        }else {
            return false;
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSku(orderLineItemsDto.getSku());
        return orderLineItems;
    }
}
