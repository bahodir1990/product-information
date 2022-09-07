package com.pim.productinformation.service.impl;

import com.pim.productinformation.model.AttributeValue;
import com.pim.productinformation.repository.AttributeValueRepository;
import com.pim.productinformation.service.AttributeValueService;
import com.pim.productinformation.service.mapper.MapStructMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttributeValueServiceImpl implements AttributeValueService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MapStructMapper structMapper;

    private final AttributeValueRepository attributeValueRepository;

    public AttributeValueServiceImpl(AttributeValueRepository attributeValueRepository) {
        this.attributeValueRepository = attributeValueRepository;
    }

    public AttributeValue save(AttributeValue attributeValue) {

        log.debug("Entering to create new AttributeValue");

        return attributeValueRepository.save(attributeValue);
        //AttributeValue attributeValue = structMapper.toAttributeValue(valueDTO);
        //attributeValueRepository.save(attributeValue);
    }

    public void saveAll(List<AttributeValue> attributeValues) {

        attributeValueRepository.saveAll(attributeValues);
        //attributeValueRepository.saveAll(structMapper.toAttributeValues(attributeValueDTOS));
    }

    @Override
    public AttributeValue update(AttributeValue object, Long id) {
        return null;
    }

    /**
     * READ AttributeValues
     *
     * @param id AttributeValues ID
     * @return the AttributeValues object
     */
    @Override
    public AttributeValue findById(Long id) {
        log.debug("Entering to get AttributeValues by id");

        Optional<AttributeValue> attribute = attributeValueRepository.findById(id);

        if (attribute.isPresent()) {
            return attribute.get();
            //return structMapper.toAttributeValueDTO(attribute.get());
        } else {
            log.warn("Unable to find the AttributeValues with ID " + id);
            return null;
        }
    }

    /**
     * FIND all AttributeValues on the database
     *
     * @return list of AttributeValues
     */
    @Override
    public List<AttributeValue> findAll() {
        log.debug("Entering to get all AttributeValues");
        //return structMapper.toAttributeValueDTOs(attributeValueRepository.findAll());
        return attributeValueRepository.findAll();
    }

    /**
     * DELETE AttributeValue by ID
     *
     * @param id the ID of AttributeValue
     */
    @Override
    public void deleteById(Long id) {
        log.debug("Entering to delete AttributeValue with ID " + id);
        attributeValueRepository.deleteById(id);
    }
}
