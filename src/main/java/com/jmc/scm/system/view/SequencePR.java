package com.jmc.scm.system.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.Expose;
import com.jmc.scm.system.service.SequenceService;
import com.jmc.scm.system.service.impl.SequenceServiceImpl;

@Component("sequencePR")
public class SequencePR {

	@Autowired
	@Qualifier(SequenceServiceImpl.BEAN_ID)
	private SequenceService sequenceService;

	@Expose
	public String generateSequence(String sequenceName) {
		return sequenceService.generateSequence(sequenceName);
	}

}
