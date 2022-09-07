package com.pim.productinformation.controller;

import com.pim.productinformation.dto.ProductDTO;
import com.pim.productinformation.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ProductService productService;

    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @RequestMapping(value = "/{product_id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProductInformation(@PathVariable("product_id")
                                                   Long productId) {
        log.debug("Entering get the product information endpoint");

        try {
            ProductDTO product = productService.findById(productId);
            if (!Objects.isNull(product)) {
                log.info("Product with ID " + productId + " is found");
                return ResponseEntity.status(HttpStatus.FOUND).body(product);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception ex) {
            log.error("Unable to find the product with ID " + productId +
                    ", message: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProducts() {

        log.debug("Entering get list of products endpoint");

        try {
            List<ProductDTO> list = productService.findAll();

            if (!Objects.isNull(list)) {
                log.info("List of products has been successfully retrieved");
                return ResponseEntity.status(HttpStatus.OK).body(list);
            } else {
                log.info("Unable to get list of products. Empty result.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception ex) {
            log.error("Unable to get list of products, message:" + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value = "/add/{category_id}", method = RequestMethod.POST)
    public ResponseEntity<?> addNewProduct(@RequestBody ProductDTO productDTO,
                                           @PathVariable("category_id") Long categoryId) {

        log.debug("Entering add product endpoint");

        try {
            ProductDTO product = productService.save(productDTO, categoryId);
            if (!Objects.isNull(product)) {
                log.info("The new product was successfully saved");
                return ResponseEntity.status(HttpStatus.CREATED).body(product);
            } else {
                log.info("Unable to save new product");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception ex) {
            log.error("Unable to save new product, message:" + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value = "/update/{product_id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO,
                                           @PathVariable("product_id") Long productId) {

        log.debug("Entering update product endpoint");

        try {
            ProductDTO product = productService.update(productDTO, productId);
            if (!Objects.isNull(product)) {
                log.info("The product was successfully updated");
                return ResponseEntity.status(HttpStatus.OK).body(product);
            } else {
                log.info("Unable to update product");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception ex) {
            log.error("Unable to update product, message:" + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value = "/delete/{product_id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProductById(@PathVariable("product_id")
                                  Long productId) {
        log.debug("Entering delete product endpoint");

        try {
            productService.deleteById(productId);
            log.info("The product with ID " + productId + " was deleted");
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception ex) {
            log.error("Unable to delete product with ID " +
                    productId + ", message:" + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
