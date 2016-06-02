package my.service.impl;

import my.dao.MoundDao;
import my.model.persist.spirit.Mound;
import my.service.MoundManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by niuyinghao on 2016/4/16 for project.
 */
@Service("moundManager")
public class MoundManagerImpl extends GenericManagerImpl<Mound,Long> implements MoundManager {

    @Autowired
    MoundManagerImpl(MoundDao dao) {
        super(dao);
    }

    @Override
    public Serializable addMound(Mound mound) {
        return dao.save(mound);
    }

	@Override
	public List getTargets(Mound mound) {
		return ((MoundDao) dao).getTargets(mound);
	}
}
