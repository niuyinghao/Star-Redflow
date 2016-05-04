package my.dao.mybatis.generic;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.RawSqlSource;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

  @Intercepts({
		  @Signature(
          type = Executor.class,
          method = "query",
          args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
      ,@Signature(
          type = Executor.class,
          method = "update",
          args = {MappedStatement.class, Object.class})
  })

  public class MappInterceptor implements Interceptor{

    private final static String _sql_regex = ".*MapperGD.*";
	  private String sql;

	  public String getSql(String boundSQL, Class<?> classz,Object param) {
			  sql = MapperSqlHelper.getExecuSQL(classz, boundSQL, param);
		  return sql;
	  }

	  @SuppressWarnings("unchecked")
	  private void processIntercept(final Object[] queryArgs) throws ClassNotFoundException {
		  final MappedStatement ms = (MappedStatement) queryArgs[0];
		  final Object parameter = queryArgs[1];
		  String mapperSQL = ms.getBoundSql(parameter).getSql();
		  BoundSql boundSQL = ms.getBoundSql(parameter);


		  boolean interceptor = mapperSQL.matches(_sql_regex);
		  if (!interceptor) {
			  return;
		  }
		  Class<?> entityclazz = MappedStatmentHelper.getEntityClazz(ms.getResource());
		  if (entityclazz == null) {
			  throw new RuntimeException("使用公共dao必須給mapper接口的@MyBatisRepository(User.class) 注解設置值.");
		  }

		/*
	  String new_sql = MapperSqlHelper.getExecuSQL(entityclazz,mapperSQL,parameter);

   BoundSql newBoundSql = MappedStatmentHelper.copyFromBoundSql(ms,
          boundSQL, new_sql);

          */
		  String sql = getSql(boundSQL.getSql(), entityclazz,parameter);
		  SqlSource sqlSource = new RawSqlSource(ms.getConfiguration(), sql, null);
		  MappedStatement newMs = MappedStatmentHelper
				  .copyFromMappedStatement(ms, sqlSource);
		  queryArgs[0] = newMs;

	  }

    public Object intercept(Invocation invocation) throws Throwable {

      processIntercept(invocation.getArgs());

      return invocation.proceed();
    }
    public Object plugin(Object o) {
          return Plugin.wrap(o, this);
    }
    public void setProperties(Properties arg0) {
    }
  }