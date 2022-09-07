package com.pim.productinformation.service.impl;

import com.pim.productinformation.dto.CategoryDTO;
import com.pim.productinformation.model.Category;
import com.pim.productinformation.repository.CategoryRepository;
import com.pim.productinformation.service.CategoryService;
import com.pim.productinformation.service.mapper.MapStructMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    public final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CategoryRepository categoryRepository;

    @Autowired
    private MapStructMapper structMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;
    }


    /**
     * CREATE new Category
     *
     * @param categoryDTO the object to save
     */
    public CategoryDTO save(CategoryDTO categoryDTO) {

        log.debug("Entering to create new category");

        Category category = structMapper.toCategory(categoryDTO);

        category.setCreatedDate(LocalDate.now());
        category.setUpdatedDate(LocalDate.now());
        Category savedCategory = categoryRepository.save(category);

        return structMapper.toCategoryDTO(savedCategory);
    }

    /**
     * UPDATE Category
     *
     * @param categoryDTO object to update
     * @param id  ID of updating category
     * @return updated category
     */
    @Override
    public CategoryDTO update(CategoryDTO categoryDTO, Long id) {

        log.debug("Entering to update the category");

        Optional<Category> category = categoryRepository.findById(id);

        if(category.isPresent()) {
            Category currentCategory = category.get();

            if(!StringUtils.isBlank(categoryDTO.getName())) {
                currentCategory.setName(categoryDTO.getName());
            }

            if(!StringUtils.isBlank(categoryDTO.getDescription())) {
                currentCategory.setDescription(categoryDTO.getDescription());
            }

            currentCategory.setUpdatedDate(LocalDate.now());

            return structMapper.toCategoryDTO(categoryRepository.save(currentCategory));
        } else {
            log.warn("Category with ID " + id + " is not found");
            return null;
        }
    }

    /**
     * READ Category
     *
     * @param id category ID
     * @return the category object
     */
    @Override
    public CategoryDTO findById(Long id) {

        log.debug("Entering to get category by id");

        Optional<Category> category = categoryRepository.findById(id);

        if(category.isPresent()) {
            return structMapper.toCategoryDTO(category.get());
        } else {
            log.warn("Unable to find the category with ID " + id);

            return null;
        }
    }


    /**
     * FIND all categories on the database
     *
     * @return list of categories
     */
    @Override
    public List<CategoryDTO> findAll() {
        log.debug("Entering to get all categories");
        return structMapper.toCategoryDTOs(categoryRepository.findAll());
    }

    /**
     * DELETE Category by ID
     *
     * @param id the ID of category
     */
    @Override
    public void deleteById(Long id) {
        log.debug("Entering to delete category with ID " + id);
        categoryRepository.deleteById(id);
    }
}
