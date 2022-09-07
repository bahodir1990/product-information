package com.pim.productinformation.controller;

import com.pim.productinformation.dto.AttributeDTO;
import com.pim.productinformation.service.impl.AttributeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/attributes")
public class AttributeController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final AttributeServiceImpl attributeService;


    public AttributeController(AttributeServiceImpl attributeService) {
        this.attributeService = attributeService;
    }

    @RequestMapping(value = "/{attribute_id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAttribute(
            @PathVariable("attribute_id") Long attributeId) {

        log.debug("Entering get the attribute endpoint");
        try {
            AttributeDTO attribute = attributeService.findById(attributeId);
            if (!Objects.isNull(attribute)) {
                log.info("Attribute with ID " + attributeId + " is found");
                return ResponseEntity.status(HttpStatus.FOUND).body(attribute);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception ex) {
            log.error("Unable to find the attribute with ID " + attributeId +
                    ", message: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAttributes() {
        log.debug("Entering get list of attributes endpoint");

        try {
            List<AttributeDTO> list = attributeService.findAll();

            if (!Objects.isNull(list)) {
                log.info("List of attributes has been successfully retrieved");
                return ResponseEntity.status(HttpStatus.OK).body(list);
            } else {
                log.info("Unable to get list of attributes. Empty result.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception ex) {
            log.error("Unable to get list of attributes, message:" + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value = "/add/{category_id}", method = RequestMethod.POST)
    public ResponseEntity<?> addNewAttribute(
            @PathVariable("category_id") Long categoryId,
            @RequestBody AttributeDTO attributeDTO) {
        log.debug("Entering add attribute endpoint");

        try {
            AttributeDTO attribute = attributeService.save(attributeDTO, categoryId);
            if (!Objects.isNull(attribute)) {
                log.info("The new attribute was successfully saved");
                return ResponseEntity.status(HttpStatus.CREATED).body(attribute);
            } else {
                log.info("Unable to save new attribute");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (Exception ex) {
            log.error("Unable to save new attribute, message:" + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value = "/delete/{attribute_id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAttributeById(@PathVariable("attribute_id")
                                                Long attributeId) {
        log.debug("Entering delete attribute endpoint");

        try {
            attributeService.deleteById(attributeId);
            log.info("The attribute with ID " + attributeId + " was deleted");
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception ex) {
            log.error("Unable to delete attribute with ID " + attributeId +
                    ", message:" + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
