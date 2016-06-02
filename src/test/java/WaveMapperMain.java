import my.dao.mybatis.mi.WaveMapper;
import my.model.persist.spirit.Wave;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by yinghao_niu on 2016/3/24 for Project.
 */

public class WaveMapperMain {

    SqlSessionFactory sqlSessionFactory;

    public static void main(String[] args) {
        try {
            new WaveMapperMain().testInsert();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public WaveMapperMain() throws IOException {

        Reader config = Resources.getResourceAsReader("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
    }

    public void testInsert() throws Exception {
        Wave wave = new Wave();
        wave.setSign("sign");
        wave.setId(789l);
        SqlSession session = sqlSessionFactory.openSession();
        WaveMapper waveMapper = session.getMapper(WaveMapper.class);
        waveMapper.insert(wave);
        session.commit();

        Wave wave1 = waveMapper.selectByPrimaryKey(123l);
        System.out.println(wave1);

    }



}
