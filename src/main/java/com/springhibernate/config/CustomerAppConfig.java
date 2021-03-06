package com.springhibernate.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.springhibernate")
@PropertySource({"classpath:persistence-mysql.properties"})
public class CustomerAppConfig implements WebMvcConfigurer {
    @Autowired
    private Environment environment;

    private Logger logger = Logger.getLogger(getClass().getName());

//    @Bean
//    public ViewResolver viewResolver(){
//        InternalResourceViewResolver viewResolver =
//                new InternalResourceViewResolver();
//        viewResolver.setPrefix("/WEB-INF/view");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }
    @Bean
    public DataSource secureDataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(environment.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        logger.info("jdbc.url>>>> " + environment.getProperty("jdbc.url"));

        dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        dataSource.setUser(environment.getProperty("jdbc.user"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));

        dataSource.setInitialPoolSize(convertStringPropertyToInt("connection.pool.initialPoolSize"));
        dataSource.setMinPoolSize(convertStringPropertyToInt("connection.pool.minPoolSize"));
        dataSource.setMaxPoolSize(convertStringPropertyToInt("connection.pool.maxPoolSize"));
        dataSource.setMaxIdleTime(convertStringPropertyToInt("connection.pool.maxIdleTime"));

        return dataSource;
    }

    private Properties getHibernateProperties() {

        // set hibernate properties
        Properties props = new Properties();

        props.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        props.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));

        return props;
    }

    private int convertStringPropertyToInt(String propertyName){
        String propertyValue = environment.getProperty(propertyName);
        int intPropertyValue = Integer.parseInt(propertyValue);
        return intPropertyValue;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){

        // create session factorys
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        // set the properties
        sessionFactory.setDataSource(secureDataSource());
        sessionFactory.setPackagesToScan(environment.getProperty("hibernate.packagesToScan"));
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

        // setup transaction manager based on session factory
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }
}
