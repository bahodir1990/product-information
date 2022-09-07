package com.pim.productinformation.service;

import com.pim.productinformation.dto.AttributeDTO;
import com.pim.productinformation.model.Attribute;

public interface AttributeService extends BaseService<AttributeDTO>{

    AttributeDTO save(AttributeDTO attributeDTO, Long categoryId);
}
