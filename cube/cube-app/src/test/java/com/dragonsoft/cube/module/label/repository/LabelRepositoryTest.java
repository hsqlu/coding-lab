package com.dragonsoft.cube.module.label.repository;

import com.dragonsoft.cube.config.DBConfig;
import com.dragonsoft.cube.config.ScheduleConfig;
import com.dragonsoft.cube.config.ServiceConfig;
import com.dragonsoft.cube.module.admin.serivce.CategoryService;
import com.dragonsoft.cube.module.modeling.entity.Model;
import com.dragonsoft.cube.module.modeling.service.ModelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created: 2016/9/6.
 * Author: Qiannan Lu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DBConfig.class, ServiceConfig.class, ScheduleConfig.class})
public class LabelRepositoryTest {

	@Autowired
	CategoryService categoryService;


	@Test
	public void test() {
		categoryService.list();
	}

}