package my.dao.mybatis.generic;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.Arrays;

/**
 * Created by yinghao_niu on 2015/12/6 0006 for Project.
 */
public class MappedStatmentHelper {
	public static MappedStatement copyFromMappedStatement(MappedStatement ms,
														  SqlSource newSqlSource) {
		MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(),
				ms.getId(), newSqlSource, ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		builder.keyProperty(Arrays.toString(ms.getKeyProperties()));
		builder.timeout(ms.getTimeout());
//		builder.parameterMap(ms.getParameterMap());

//		Collection<ResultMap> resultMaps1 = ms.getConfiguration().getResultMaps();
//		List<ResultMap> resultMaps = new ArrayList<>();
//		for (ResultMap resultMap : resultMaps1) {
//			resultMaps.add(resultMap);
//		}
//		resultMaps.add(ms.getConfiguration().getResultMap("BaseResultMap"));

		builder.resultMaps(ms.getResultMaps());

		builder.resultSetType(ms.getResultSetType());
		builder.cache(ms.getCache());
		MappedStatement newMs = builder.build();
		return newMs;
	}

	public static Class<?> getEntityClazz(String resource) throws ClassNotFoundException {

		String classname = resource.substring(0,resource.lastIndexOf("."));
		classname = classname.replaceAll("/", ".");
		Class interfaceClass = Class.forName(classname);
		MyBatisRepository annotation = (MyBatisRepository) interfaceClass.getAnnotation(MyBatisRepository.class);
		Class value = annotation.value();
		return value;

	}
}
