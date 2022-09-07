package com.pim.productinformation.service.mapper;

import com.pim.productinformation.dto.*;
import com.pim.productinformation.model.Attribute;
import com.pim.productinformation.model.AttributeValue;
import com.pim.productinformation.model.Category;
import com.pim.productinformation.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface MapStructMapper {

    ProductDTO toProductDTO(Product product);

    List<ProductDTO> toProductDTOs(List<Product> products);

    Product toProduct(ProductDTO productDTO);

    ProductCategoryDTO toProductCategoryDTO(Category category);

    Category toCategory(ProductCategoryDTO productCategoryDTO);

    Category toCategory(CategoryDTO categoryDTO);

    CategoryDTO toCategoryDTO(Category category);

    List<CategoryDTO> toCategoryDTOs(List<Category> categories);

    AttributeDTO toAttributeDTO(Attribute attribute);

    Attribute toAttribute(AttributeDTO attributeDTO);

    List<AttributeDTO> toAttributeDTOs(List<Attribute> attributes);

    AttributeValueDTO toAttributeValueDTO(AttributeValue attributeValue);

    AttributeValue toAttributeValue(AttributeValueDTO attributeValueDTO);

    List<AttributeValueDTO> toAttributeValueDTOs(List<AttributeValue> attributeValues);

    List<AttributeValue> toAttributeValues(List<AttributeValueDTO> attributeValueDTOS);
}
