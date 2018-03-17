package com.dragonsoft.cube.module.admin.repository;

import com.dragonsoft.cube.module.admin.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created: 20/09/2016.
 * Author: Qiannan Lu
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
