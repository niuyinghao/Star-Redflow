package my.service.impl;

import my.dao.*;
import my.model.persist.BaseLog;
import my.model.persist.BaseObj;
import my.model.persist.spirit.*;
import my.service.AreaManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yinghao_niu on 2016/4/24 for Project.
 */
@Service("areaManager")
public class AreaManagerImpl implements AreaManager {
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	@Autowired
	private MiscDao miscDao;
	@Autowired
	private WishDao wishDao;
	@Autowired
	private FlowerDao flowerDao;
	@Autowired
	private StoneDao stoneDao;
	@Autowired
	private MoundDao moundDao;
	@Autowired
	@Qualifier("waveDao")
	private WaveDao waveDao;

	@Override
	public List getMoundTarget() {
		return miscDao.getMoundTarget();
	}

	@Override
	public BaseLog getBaseLogById(String id) {
		return miscDao.getBaseLogById(Long.valueOf(id));
	}

	@Override
	public List getPagedList(Class type, Object context) throws NoSuchFieldException {
		if (type.equals(Wave.class)) {
			return waveDao.getPagedList(context);
		} else if (type.equals(Wish.class)) {
			return wishDao.getPagedList(context);
		} else if (type.equals(Flower.class)) {
			return flowerDao.getPagedList(context);
		} else if (type.equals(Stone.class)) {
			return stoneDao.getPagedList(context);
		} else if (type.equals(Mound.class)) {
			return moundDao.getPagedList(context);
		}
		return null;
	}

	@Override
	public int getCount(Class type) {
		if (type.equals(Wave.class)) {
			return waveDao.getCount();
		} else if (type.equals(Wish.class)) {
			return wishDao.getCount();
		} else if (type.equals(Flower.class)) {
			return flowerDao.getCount();
		} else if (type.equals(Stone.class)) {
			return stoneDao.getCount();
		} else if (type.equals(Mound.class)) {
			return moundDao.getCount();
		}

		return 0;
	}

    @Override
    public void updateBaseObj(BaseObj baseObj) {
        if (baseObj == null || baseObj.getId()==null) {
            return;
        }
        miscDao.update(baseObj);
    }

	@Override
	public Session _getSession() {
		return miscDao.getSession();
	}

	@Override
    public List getAllWaveOrFlowerNotMound() {
        return miscDao.getAllWaveOrFlowerNotMound();
    }
}
