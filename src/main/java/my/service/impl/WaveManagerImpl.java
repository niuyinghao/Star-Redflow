package my.service.impl;

import my.dao.WaveDao;
import my.model.persist.place.Wave;
import my.service.WaveManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yinghao_niu on 2016/4/2 for Project.
 */
@Service("waveManager")
public class WaveManagerImpl extends GenericManagerImpl<Wave, Long> implements WaveManager {

//constructs
	@Autowired
	WaveManagerImpl(WaveDao dao) {
		super(dao);
	}
}
