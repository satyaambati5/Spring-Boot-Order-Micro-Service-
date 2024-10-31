package org.ambati.order_service.service;

import org.ambati.order_service.model.Product;
import org.ambati.order_service.model.ProductQuantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class ProductServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceClient.class);

    @Autowired
    private final RestTemplate restTemplate;

    private final String productServiceUrl;

    public ProductServiceClient(RestTemplate restTemplate, @Value("${product.service.url}") String productServiceUrl) {
        this.restTemplate = restTemplate;
        this.productServiceUrl = productServiceUrl;
    }

    public Product getProductById(long productId) {
        return restTemplate.getForObject(productServiceUrl +"/products" + "/" + productId, Product.class);
    }

    public Product getProductByParams( Long productId) {
        return restTemplate.getForObject(productServiceUrl +"/products" + "/" + productId, Product.class);
    }

    public String getOrderInvoice(List<ProductQuantity> productQuantities){

        logger.info("Request to calculate order invoice: {}", productQuantities);
        try {
            String response = restTemplate.postForObject(productServiceUrl+"/calculate-order", productQuantities, String.class);
            logger.info("Received product response: {}", response);
            return response;
        } catch (RestClientException e) {
            logger.error("Error calling calculate-order API", e);
            throw e;
        }

    }


}