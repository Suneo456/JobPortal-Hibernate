package com.jobportal.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

  private static final SessionFactory sessionFactory =
      buildSessionFactory();

  private static SessionFactory buildSessionFactory() {

    try {

      return new Configuration()
          .configure("hibernate.cfg.xml")
          .buildSessionFactory();

    } catch (Throwable e) {

      System.out.println("SessionFactory creation failed");

      e.printStackTrace();

      throw new ExceptionInInitializerError(e);
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public static void shutdown() {
    getSessionFactory().close();
  }
}