package com.jmc.scm.system.service;

import com.jmc.scm.system.model.SysSequence;

public interface SequenceService {

	String generateSequence(String sequenceName);

	void updateSequence(SysSequence sequence);

}
