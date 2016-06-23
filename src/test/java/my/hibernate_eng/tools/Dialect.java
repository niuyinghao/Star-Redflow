package my.hibernate_eng.tools;

enum Dialect {
MYSQL("org.hibernate.dialect.MySQLDialect"),
ORACLE("org.unhcr.omss.db.oracle.OracleDialectDeferredFK"), 
SYBASE("org.hibernate.dialect.SybaseAnywhereDialect"),
	H2("org.hibernate.dialect.H2Dialect")
    ,
    POSTGRES("org.hibernate.dialect.PostgreSQL9Dialect");

private String className;

private Dialect(String className) {
	this.className = className;
}

public String getClassName() {
    return className;
}
   }
