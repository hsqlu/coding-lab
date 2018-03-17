package com.dragonsoft.cube.module.admin.serivce;

import com.dragonsoft.cube.module.admin.entity.Category;
import com.dragonsoft.cube.module.admin.repository.CategoryItemRepository;
import com.dragonsoft.cube.module.admin.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created: 20/09/2016.
 * Author: Qiannan Lu
 */
@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryItemRepository categoryItemRepository;

	public void list() {
		Category category = new Category();
		category.setName("人员要素");
		category.setType("要素分类");
		categoryRepository.save(category);
	}
}
