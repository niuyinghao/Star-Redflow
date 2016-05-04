package my.dao.mybatis.generic;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by yinghao_niu on 2015/12/6 0006 for Project.
 */
@Retention(RetentionPolicy.RUNTIME)

public @interface MyBatisColumn {
	boolean isAutoKey() default true;
	 String value() default "";
	boolean isID();
}
