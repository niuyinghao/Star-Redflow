package my.model.persist.project;

import my.BaseTest;
import my.dao.MiscDao;
import my.dao.hibernate.MiscDaoHibernate;
import my.service.MiscManager;
import my.service.impl.MiscManagerImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

/**
 * Created by yinghao_niu on 2016/6/29 for project.
 */

public class HeartSymbolTest extends BaseTest{

    @Autowired
    private MiscManager miscManager;

    @Test
    @Transactional
    public void testAddAge() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        HeartSymbol heartSymbol = new HeartSymbol();
        heartSymbol.setBelong(null);
        heartSymbol.setAge(5);
        heartSymbol.setId(null);
        miscManager.saveOrUpdate(heartSymbol);

    }

}