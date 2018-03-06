package com.dragonsoft.cube.module.label.repository;

import com.dragonsoft.cube.module.label.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created: 2016/9/2.
 * Author: Qiannan Lu
 */
@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {

}
