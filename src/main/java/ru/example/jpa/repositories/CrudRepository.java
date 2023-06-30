package ru.example.jpa.repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import ru.example.jpa.config.HibernateConf;

import java.util.function.Function;

@Repository
public abstract class CrudRepository {
    private final HibernateConf hibernateConf;

    public CrudRepository(HibernateConf hibernateConf) {
        this.hibernateConf = hibernateConf;
    }

    protected <T> T execute(final Function<Session, T> command, Logger logger) {
        Transaction transaction = null;
        try (Session session = hibernateConf.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            T rsl = command.apply(session);
            transaction.commit();
            logger.info("Записали некоторое говно");
            return rsl;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                logger.error("Случилось некоторое дерьмо, делаем rollback, {}", e.getMessage());
            }
            throw e;
        }
    }
}
