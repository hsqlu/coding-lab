package com.dragonsoft.cube.module.modeling.repository;

import com.dragonsoft.cube.module.modeling.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created: 2016/9/7.
 * Author: Qiannan Lu
 */
@Repository
public interface ModelRepository extends JpaRepository<Model, String> {
	Model findOneByCreateBy(String createBy);
}
