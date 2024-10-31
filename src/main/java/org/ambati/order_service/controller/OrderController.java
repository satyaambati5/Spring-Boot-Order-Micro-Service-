package org.ambati.order_service.controller;

import org.ambati.order_service.model.Product;
import org.ambati.order_service.model.ProductQuantity;
import org.ambati.order_service.service.ProductServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class OrderController {


    @Autowired
    private  ProductServiceClient productServiceClient;

    @GetMapping("/orders/{productId}")
    public String createOrder(@PathVariable Long productId) {
        Product product = productServiceClient.getProductById(productId);
        return "Order created and Forwarded to chef Please wait....:  \n product name : " + product.getName() + " Please Pay price: $ " + product.getPrice();
    }
    @GetMapping("/orders")
    public String createOrder(@RequestParam long productId) {
        Product product = productServiceClient.getProductByParams(productId);
        return "Order created and Forwarded to chef Please wait....:  \n product name : " + product.getName() + " Please Pay price: $ " + product.getPrice();
    }


    @PostMapping("/orders/calculate-total")
    public String calculateTotalOrderCost(@RequestBody List<ProductQuantity> productQuantities) {
        String product = productServiceClient.getOrderInvoice(productQuantities);


        return "Order created and Forwarded to chef Please wait....:  \n  Please Pay price: $ " + product;

    }




}