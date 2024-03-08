package org.example.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        SessionFactory factory;
        try {
            // Creation de sessionFactory pour hibernate.cfg.xml
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            // Il faut souligner que l'exception doit etre elevé
            System.err.println("Echec de la creation." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        sessionFactory = factory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
