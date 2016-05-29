package my.hibernate_eng.tools;

import my.model.persist.*;
import my.model.persist.board.Article;
import my.model.persist.board.Dislike;
import my.model.persist.board.Favour;
import my.model.persist.place.*;
import my.model.persist.project.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.junit.Test;


public class HibernateDDLGenerator {

	// members
	Class[] classes = new Class[]{Achievement.class,
			Article.class,
			BaseLog.class,
			BaseObject.class,
			Flower.class,
			Mound.class,
			Pray.class,
			Punishment.class,
			Role.class,
			Context.class,
			Stone.class,
			User.class,
			Wave.class,
			Dislike.class,
			Favour.class,
			Context.class,
			Music.class,
			Wish.class ,
	};

	@Test
	public void doCreate() {
		new HibernateDDLGenerator().create(
				Dialect.MYSQL,
				classes
		);
	}

	//method
	private void create(Dialect dialect, Class<?>... classes) {
		Configuration configuration = getConfiguration(dialect, classes);
		create(dialect, configuration);
	}

	private Configuration getConfiguration(Dialect dialect, Class<?>[] classes) {
		Configuration configuration = new Configuration();
		configuration.setProperty("hibernate.connection.url", "jdbc:h2:tcp://127.0.0.1:8043/./src/test/data/db");
		configuration.setProperty("hibernate.connection.username", "sa");
		configuration.setProperty("hibernate.connection.password", "");
		configuration.setProperty(Environment.DIALECT, dialect.getClassName());
		configuration.setNamingStrategy(new ImprovedNamingStrategy());
		for (Class<?> entityClass : classes) {
			configuration.addAnnotatedClass(entityClass);
		}
		return configuration;
	}

	private void create(Dialect dialect, Configuration configuration) {
		SchemaExport schemaExport = new SchemaExport(configuration);

		schemaExport.setDelimiter(";");
//		schemaExport.setOutputFile(String.format("%s_%s.%s ", new Object[]{"ddl", dialect.name().toLowerCase(), "sql"}));
		boolean consolePrint = true;
		boolean exportInDatabase = false;
		schemaExport.create(consolePrint, exportInDatabase);
	}

	@Test
	public void doUpdate() {
		new HibernateDDLGenerator().update(Dialect.MYSQL, classes);
	}

	private void update(Dialect mysql, Class[] classes) {
		Configuration configuration = getConfiguration(mysql, classes);
		update(mysql, configuration);
	}

	private void update(Dialect dialect, Configuration configuration) {
		SchemaUpdate schemaExport = new SchemaUpdate(configuration);
		schemaExport.setDelimiter(";");
//		schemaExport.setOutputFile(String.format("%s_%s.%s ", new Object[]{"ddl", dialect.name().toLowerCase(), "sql"}));
		boolean consolePrint = true;
		boolean exportInDatabase = true;
		schemaExport.execute(consolePrint, exportInDatabase);
	}
}
