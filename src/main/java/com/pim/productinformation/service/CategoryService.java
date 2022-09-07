package com.pim.productinformation.service;

import com.pim.productinformation.dto.CategoryDTO;
import com.pim.productinformation.model.Category;

public interface CategoryService extends BaseService<CategoryDTO>{

    CategoryDTO save(CategoryDTO categoryDTO);
}
