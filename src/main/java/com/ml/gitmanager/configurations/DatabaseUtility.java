package com.ml.gitmanager.configurations;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.springframework.boot.jdbc.DataSourceBuilder;

import com.ml.gitmanager.utilities.GitManagerUtilities;

import ch.qos.logback.classic.Level;

public class DatabaseUtility {
	
	private static SessionFactory factory;
	private static final Logger LOG = GitManagerUtilities.getPersonalizedLogger(DatabaseUtility.class, Level.TRACE);

	private DatabaseUtility() {
		//private constructor
	}
	
	static {
		try(InputStream inStream = new FileInputStream("application.properties")){
			
			Properties props = new Properties();
			props.load(inStream);
			DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create(DatabaseUtility.class.getClassLoader());
			dataSourceBuilder.username(null);
			
		}catch (Exception e) {
			LOG.info("Exception occurred while configuring the database.");
			GitManagerUtilities.detailedStacktrace(e);
		}
	}
}
