package my.dao.mybatis;

import java.util.List;

/**
 * Created by yinghao_niu on 2016/4/3 for Project.
 */
public interface Mapper<T> {
//method
	List<T> selectByExample();
}
