package ru.example.jpa.config;

import jakarta.annotation.PostConstruct;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Configuration
@PropertySource("application.properties")
@Component
public class HibernateConf {

    @Value("${hibernate.dialect}")
    private String postgreDialect;
    @Value("${hibernate.connection.driver_class}")
    private String postgreDriver;
    @Value("${hibernate.connection.url}")
    private String postgreURL;
    @Value("${hibernate.connection.username}")
    private String postgreUser;
    @Value("${hibernate.connection.password}")
    private String postgrePassword;
    @Value("${hibernate.show_sql}")
    private String postgreShowSQL;
    @Value("${hibernate.hbm2ddl}")
    private String postgreHdm2ddl;

    private SessionFactory sessionFactory;

    @PostConstruct
    public SessionFactory init() {
        try {
            if (sessionFactory == null) {
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(this.getHibernateProperties())
                        .build();
                sessionFactory = new MetadataSources(serviceRegistry)
                        .addAnnotatedClassName("ru.example.jpa.entities.UserEntity")
                        .addAnnotatedClassName("ru.example.jpa.entities.MessageEntity")
                        .addAnnotatedClassName("ru.example.jpa.entities.ChatEntity")
                        .buildMetadata()
                        .buildSessionFactory();
            }

            return sessionFactory;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put(AvailableSettings.DIALECT, postgreDialect);
        prop.put(AvailableSettings.DRIVER, postgreDriver);
        prop.put(AvailableSettings.URL, postgreURL);
        prop.put(AvailableSettings.USER, postgreUser);
        prop.put(AvailableSettings.PASS, postgrePassword);
        prop.put("hibernate.hbm2ddl", postgreHdm2ddl);
        prop.put(AvailableSettings.SHOW_SQL, postgreShowSQL);
        return prop;
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void shutdown() {
        getSessionFactory().close();
    }
}
