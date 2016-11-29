package com.cbo.school.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.postgresql.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DataBaseConfig {


    public DataSource inMemory(){
    	EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		return builder
			.setType(EmbeddedDatabaseType.HSQL) //.H2 or .DERBY
			//.addScript("db/sql/create-db.sql")
			//.addScript("db/sql/insert-data.sql")
			.build();
    }
    
    @Bean
    @Primary
    public DataSource dataSource() throws ClassNotFoundException {
    	
        //String dbUrl = System.getenv("JDBC_DATABASE_URL");
        String dbUrl = ("jdbc:postgresql://127.0.0.1:5433/school");
        //String username = System.getenv("JDBC_DATABASE_USERNAME");
        String username = ("postgres");
        //String password = System.getenv("JDBC_DATABASE_PASSWORD");
        String password = ("root");

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        
        
        Class.forName(Driver.class.getCanonicalName());
        basicDataSource.setDriverClassName("org.postgresql.Driver");
        basicDataSource.setInitialSize(1);

        return basicDataSource;
    }
	
}
