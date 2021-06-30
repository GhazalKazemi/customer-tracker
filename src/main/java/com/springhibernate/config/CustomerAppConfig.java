package com.springhibernate.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.springhibernate")
@PropertySource("classpath:persistence-mysql.properties")
public class CustomerAppConfig {
    @Autowired
    private Environment environment;

    private Logger logger = Logger.getLogger(getClass().getName());

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    public DataSource secureDataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(environment.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        logger.info("jdbc.url>>>> " + environment.getProperty("jdbc.url"));

        dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        dataSource.setJdbcUrl(environment.getProperty("jdbc.user"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));

        dataSource.setInitialPoolSize(convertStringPropertyToInt("connection.pool.initialPoolSize"));
        dataSource.setMinPoolSize(convertStringPropertyToInt("connection.pool.minPoolSize"));
        dataSource.setMaxPoolSize(convertStringPropertyToInt("connection.pool.maxPoolSize"));
        dataSource.setMaxIdleTime(convertStringPropertyToInt("connection.pool.maxIdleTime"));

        return dataSource;
    }

    private int convertStringPropertyToInt(String propertyName){
        String propertyValue = environment.getProperty(propertyName);
        int intPropertyValue = Integer.parseInt(propertyValue);
        return intPropertyValue;
    }
}
