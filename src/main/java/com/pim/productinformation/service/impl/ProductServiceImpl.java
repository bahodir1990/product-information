package com.pim.productinformation.service.impl;

import com.pim.productinformation.dto.ProductAttributesDTO;
import com.pim.productinformation.dto.ProductDTO;
import com.pim.productinformation.model.Attribute;
import com.pim.productinformation.model.AttributeValue;
import com.pim.productinformation.model.Category;
import com.pim.productinformation.model.Product;
import com.pim.productinformation.repository.*;
import com.pim.productinformation.service.AttributeValueService;
import com.pim.productinformation.service.CategoryService;
import com.pim.productinformation.service.ProductService;
import com.pim.productinformation.service.mapper.MapStructMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.commons.lang3.StringUtils;


import java.time.LocalDate;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    public final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    private final AttributeValueService attributeValueService;

    @Autowired
    private MapStructMapper structMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryService categoryService,
                              AttributeValueService attributeValueService) {

        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.attributeValueService = attributeValueService;
    }

    /**
     * CREATE Product
     *
     * @param productDTO the object to save
     * @param categoryId ID of the category in which the new product belongs
     */
    public ProductDTO save(ProductDTO productDTO, Long categoryId) {

        log.debug("Entering to create new product");

        Category category = structMapper.toCategory(
                categoryService.findById(categoryId));

        if (!Objects.isNull(category)) {
            Product product = structMapper.toProduct(productDTO);
            product.setCategory(category);
            product.setCreatedDate(LocalDate.now());
            product.setUpdatedDate(LocalDate.now());
            Product p = productRepository.save(product);

            List<ProductAttributesDTO> productAttributesDTOS =
                    productDTO.getAttributes();

            if(productAttributesDTOS != null
                    && productAttributesDTOS.size() != 0) {
                saveAttributeValues(p, productAttributesDTOS);
            }

            return structMapper.toProductDTO(p);
        } else {
            log.warn("Category with ID " + categoryId + " is not found");
            return null;
        }
    }

    private void saveAttributeValues(Product p,
                                     List<ProductAttributesDTO> productAttributesDTOS) {
        log.debug("Entering to save attribute values");
        // Get list of attributes of the current category
        List<Attribute> attributes = p.getCategory().getAttributes();

        // Mapping all attributes, where IDs are the key and the object
        // itself is the value
        Map<Long, Attribute> attributeIds = new HashMap<>();
        attributes.forEach(att->attributeIds.put(att.getId(), att));

        // Creates a list of attribute values, checking if the product
        // actually has such an attribute. If not,
        // the current attribute value is not saved.
        List<AttributeValue> attributeValues = new ArrayList<>();

        for (ProductAttributesDTO pa:productAttributesDTOS) {
            if(attributeIds.containsKey(pa.getAttributeId())) {

                attributeValues.add(new AttributeValue(
                        pa.getAttributeValue(),
                        attributeIds.get(pa.getAttributeId()),
                        p
                ));
            } else {
                log.warn("The product has no such attribute with ID "
                        + pa.getAttributeId());
            }
        }
        attributeValueService.saveAll(attributeValues);
    }

    /**
     * UPDATE Product
     *
     * @param productDTO object to update
     * @param productId  ID of updating product
     * @return updated product
     */

    @Override
    public ProductDTO update(ProductDTO productDTO, Long productId) {

        log.debug("Entering to update the product");

        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            Product currentProduct = product.get();

            if (!StringUtils.isBlank(productDTO.getName())) {
                currentProduct.setName(productDTO.getName());
            }
            if (!StringUtils.isBlank(productDTO.getDescription())) {
                currentProduct.setDescription(productDTO.getDescription());
            }

            if (!StringUtils.isBlank(productDTO.getSku())) {
                currentProduct.setSku(productDTO.getSku());
            }
            if (productDTO.getUnitsInStock() != null) {
                currentProduct.setUnitsInStock(productDTO.getUnitsInStock());
            }

            if (productDTO.getPrice() != null) {
                currentProduct.setPrice(productDTO.getPrice());
            }

            if (productDTO.getCategory() != null &&
                    !productDTO.getCategory().getId().equals(
                            currentProduct.getCategory().getId())) {
                currentProduct.setCategory(structMapper.toCategory(productDTO.getCategory()));
            }

            currentProduct.setUpdatedDate(LocalDate.now());

            Product p = productRepository.save(currentProduct);

            if (productDTO.getAttributes() != null &&
                    productDTO.getAttributes().size() != 0) {
                saveAttributeValues(currentProduct, productDTO.getAttributes());
            }

            return structMapper.toProductDTO(p);
        } else {
            log.warn("Product with ID " + productId + " is not found");
            return null;
        }
    }

    /**
     * READ Product
     *
     * @param id product ID
     * @return the product object which contains product information
     */
    @Override
    public ProductDTO findById(Long id) {

        log.debug("Entering to get product by id");

        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            ProductDTO productDTO = structMapper.toProductDTO(product.get());
            List<Object[]> productAttributeDetails = productRepository.
                    findAttributes(product.get().getCategory().getId());

            List<ProductAttributesDTO> attributes = new ArrayList<>();

            productAttributeDetails.forEach(obj ->
                    attributes.add(new ProductAttributesDTO(
                            Long.valueOf(obj[0].toString()),
                            obj[1].toString(),
                            obj[2].toString())));
            productDTO.setAttributes(attributes);

            return productDTO;
        } else {
            log.info("Unable to find the product with ID " + id);

            return null;
        }
    }

    /**
     * FIND all products on the database
     *
     * @return list of products
     */
    @Override
    public List<ProductDTO> findAll() {
        log.debug("Entering to get all products");
        return structMapper.toProductDTOs(productRepository.findAll());
    }

    /**
     * DELETE Product by ID
     *
     * @param id the ID of product
     */
    @Override
    public void deleteById(Long id) {
        log.debug("Entering to delete product with ID " + id);
        productRepository.deleteById(id);
    }
}
