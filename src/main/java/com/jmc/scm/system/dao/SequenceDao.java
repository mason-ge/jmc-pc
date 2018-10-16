package com.jmc.scm.system.dao;

import com.jmc.scm.system.model.SysSequence;

public interface SequenceDao {

	SysSequence findSequence(String sequenceName);

	void updateSequnce(SysSequence alloveSequence);

}
