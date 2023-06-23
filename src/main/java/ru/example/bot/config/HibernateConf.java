package ru.example.bot.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.example.jpa.config.BotConfigg;

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
    @Value("${bot.name}")
    String botName;

    private SessionFactory sessionFactory;

    @PostConstruct
    public SessionFactory init() {
        try {
            if (sessionFactory == null) {
                StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                        .configure()
                        .build();

                sessionFactory = new MetadataSources(standardRegistry).buildMetadata().buildSessionFactory();
            }
            return sessionFactory;
        } catch (Throwable ex) {
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
