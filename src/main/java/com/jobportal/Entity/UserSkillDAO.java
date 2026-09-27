package com.jobportal.Entity;

import com.jobportal.config.HibernateUtil;
import com.jobportal.model.UserSkill;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserSkillDAO {

  public void addSkill(UserSkill userSkill) {

    Transaction transaction = null;

    try (Session session = HibernateUtil.getSessionFactory().openSession()) {

      transaction = session.beginTransaction();

      session.persist(userSkill);

      transaction.commit();

      System.out.println("Skill added successfully!");

    } catch (Exception e) {

      if (transaction != null) {
        transaction.rollback();
      }

      e.printStackTrace();
    }
  }

  public List<UserSkill> getSkillsByUserId(int userId) {

    try (Session session = HibernateUtil.getSessionFactory().openSession()) {

      return session.createQuery(
          "FROM UserSkill WHERE userId = :userId",
          UserSkill.class
      ).setParameter("userId", userId).list();
    }
  }
}