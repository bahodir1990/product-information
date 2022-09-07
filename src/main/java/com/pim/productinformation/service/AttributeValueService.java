package com.pim.productinformation.service;

import com.pim.productinformation.dto.AttributeValueDTO;
import com.pim.productinformation.model.AttributeValue;

import java.util.List;

public interface AttributeValueService extends BaseService<AttributeValue>{
    AttributeValue save(AttributeValue attributeValue);

    void saveAll(List<AttributeValue> attributeValues);

}
