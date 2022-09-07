package com.pim.productinformation.service;

import com.pim.productinformation.dto.ProductDTO;
import com.pim.productinformation.model.Product;

public interface ProductService extends BaseService<ProductDTO>{
    ProductDTO save(ProductDTO productDTO, Long categoryId);
}
