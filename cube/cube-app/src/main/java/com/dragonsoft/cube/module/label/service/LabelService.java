package com.dragonsoft.cube.module.label.service;

import com.dragonsoft.cube.module.label.entity.Label;
import com.dragonsoft.cube.module.label.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created: 2016/9/2.
 * Author: Qiannan Lu
 */
@Service
public class LabelService {
	@Autowired
	private LabelRepository labelRepository;

	public void addLabel(Label label) {

	}

	public void deleteLabel(Label label) {

	}
}
