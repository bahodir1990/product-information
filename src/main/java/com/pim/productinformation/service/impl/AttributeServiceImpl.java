package com.pim.productinformation.service.impl;

import com.pim.productinformation.dto.AttributeDTO;
import com.pim.productinformation.dto.CategoryDTO;
import com.pim.productinformation.model.Attribute;
import com.pim.productinformation.repository.AttributeRepository;
import com.pim.productinformation.service.AttributeService;
import com.pim.productinformation.service.mapper.MapStructMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AttributeServiceImpl implements AttributeService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MapStructMapper structMapper;

    private final AttributeRepository attributeRepository;

    private final CategoryServiceImpl categoryService;

    public AttributeServiceImpl(AttributeRepository attributeRepository,
                                CategoryServiceImpl categoryService) {
        this.attributeRepository = attributeRepository;
        this.categoryService = categoryService;
    }


    /**
     * CREATE Attribute
     *
     * @param attributeDTO the object to save
     * @param categoryId ID of the category in which the new attribute belongs
     */
    public AttributeDTO save(AttributeDTO attributeDTO, Long categoryId) {
        log.debug("Entering to create new attribute");

        CategoryDTO category = categoryService.findById(categoryId);

        if (category != null) {
            Attribute attribute = structMapper.toAttribute(attributeDTO);
            attribute.setCategory(structMapper.toCategory(category));

            Attribute a = attributeRepository.save(attribute);

            return structMapper.toAttributeDTO(a);
        }

        return null;
    }

    @Override
    public AttributeDTO update(AttributeDTO object, Long id) {
        return null;
    }

    /**
     * READ Attribute
     *
     * @param id attribute ID
     * @return the attribute object
     */
    @Override
    public AttributeDTO findById(Long id) {
        log.debug("Entering to get attribute by id");

        Optional<Attribute> attribute = attributeRepository.findById(id);

        if(attribute.isPresent()) {
            return structMapper.toAttributeDTO(attribute.get());
        } else {
            log.warn("Unable to find the attribute with ID " + id);
            return null;
        }
    }

    /**
     * FIND all attributes on the database
     *
     * @return list of attributes
     */
    @Override
    public List<AttributeDTO> findAll() {
        log.debug("Entering to get all attributes");
        return structMapper.toAttributeDTOs(attributeRepository.findAll());
    }

    /**
     * DELETE Attribute by ID
     *
     * @param id the ID of attribute
     */
    @Override
    public void deleteById(Long id) {
        log.debug("Entering to delete attribute with ID " + id);
        attributeRepository.deleteById(id);
    }
}
