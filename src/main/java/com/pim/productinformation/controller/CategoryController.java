package com.pim.productinformation.controller;

import com.pim.productinformation.dto.CategoryDTO;
import com.pim.productinformation.service.CategoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @RequestMapping(value = "/{category_id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCategory(@PathVariable("category_id")
                                         Long categoryId) {
        log.debug("Entering get the category endpoint");

        try {
            CategoryDTO category = categoryService.findById(categoryId);
            if (!Objects.isNull(category)) {
                log.info("Category with ID " + categoryId + " is found");
                return ResponseEntity.status(HttpStatus.FOUND).body(category);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception ex) {
            log.error("Unable to find the category with ID " + categoryId +
                    ", message: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCategories() {
        log.debug("Entering get list of categories endpoint");

        try {
            List<CategoryDTO> list = categoryService.findAll();

            if (!Objects.isNull(list)) {
                log.info("List of categories has been successfully retrieved");
                return ResponseEntity.status(HttpStatus.OK).body(list);
            } else {
                log.info("Unable to get list of categories. Empty result.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception ex) {
            log.error("Unable to get list of categories, message:" + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //@PostMapping("/add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addNewCategory(
            @RequestBody CategoryDTO categoryDTO) {
        log.debug("Entering add category endpoint");

        try {
            CategoryDTO category = categoryService.save(categoryDTO);
            if (!Objects.isNull(category)) {
                log.info("The new category was successfully saved");
                return ResponseEntity.status(HttpStatus.CREATED).body(category);
            } else {
                log.info("Unable to save new category");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (Exception ex) {
            log.error("Unable to save new category, message:" + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    //@PutMapping("/update/{category_id}")
    @RequestMapping(value = "/update/{category_id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDTO categoryDTO,
                                           @PathVariable("category_id") Long categoryId) {

        log.debug("Entering update category endpoint");

        try {
            CategoryDTO category = categoryService.update(categoryDTO, categoryId);
            if (!Objects.isNull(category)) {
                log.info("The category was successfully updated");
                return ResponseEntity.status(HttpStatus.OK).body(category);
            } else {
                log.info("Unable to update category");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (Exception ex) {
            log.error("Unable to update category with ID" +
                    categoryId + ", message:" + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //@DeleteMapping("/delete/{category_id}")
    @RequestMapping(value = "/delete/{category_id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategoryById(@PathVariable("category_id")
                                            Long categoryId) {
        log.debug("Entering delete category endpoint");

        try {
            categoryService.deleteById(categoryId);
            log.info("The category with ID " + categoryId + " was deleted");
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception ex) {
            log.error("Unable to delete category with ID " + categoryId +
                    ", message:" + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
