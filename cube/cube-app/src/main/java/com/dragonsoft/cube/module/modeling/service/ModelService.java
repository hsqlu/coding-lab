package com.dragonsoft.cube.module.modeling.service;

import com.dragonsoft.cube.module.modeling.entity.Model;
import com.dragonsoft.cube.module.modeling.entity.ModelFollower;
import com.dragonsoft.cube.module.modeling.entity.ModelTask;
import com.dragonsoft.cube.module.modeling.repository.ModelFollowerRepository;
import com.dragonsoft.cube.module.modeling.repository.ModelRepository;
import com.dragonsoft.cube.module.modeling.repository.TaskRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created: 2016/9/2.
 * Author: Qiannan Lu
 */
@Service
public class ModelService {

	@Autowired
	private ModelRepository modelRepository;

	@Autowired
	private ModelFollowerRepository followerRepository;

	@Autowired
	private TaskRepository taskRepository;

	public void createModel() {
		Model model = new Model();
		model.setCreateBy("tests");
		model.setContext("test model context");
		modelRepository.save(model);
	}

	public void createTask(Model model) {
		ModelTask modelTask = new ModelTask();
		modelTask.setModel(model);
		taskRepository.save(modelTask);
	}

	public Model findOneByCreateBy(String createBy) {
		return modelRepository.findOneByCreateBy(createBy);
	}

	public void followModel(String modelId) {
		ModelFollower follower = new ModelFollower();
		follower.setModelId(modelId);
		follower.setUserId("tests");
		followerRepository.save(follower);
	}

	public List<String> fetchModelsByFollower(String userId) {
		return Lists.newArrayList();
	}

	public List<String> fetchModelsByCreator(String userId) {
		return Lists.newArrayList();
	}

	public List<String> fetchPopularModels() {
		return Lists.newArrayList();
	}

	public List historicalResults() {
		return Lists.newArrayList();
	}
}
