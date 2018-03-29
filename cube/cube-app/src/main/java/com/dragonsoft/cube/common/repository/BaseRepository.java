package com.dragonsoft.cube.common.repository;

import com.dragonsoft.cube.common.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created: 2016/9/6.
 * Author: Qiannan Lu
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity<I>, I extends Serializable> extends JpaRepository<T, I> {

}