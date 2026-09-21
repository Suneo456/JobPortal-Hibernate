package com.jobportal.Entity;

import com.jobportal.config.HibernateUtil;
import com.jobportal.model.JobTechStack;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class JobTechStackDAO {

    // CREATE
    public void addTechnology(JobTechStack tech) {

        Transaction transaction = null;

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(tech);

            transaction.commit();

            System.out.println("Technology added successfully.");

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println("Could not add technology: "
                + e.getMessage());
        }
    }

    // READ ALL
    public void getAllTechnologies() {

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            List<JobTechStack> technologies =
                session.createQuery(
                    "FROM JobTechStack ORDER BY techId",
                    JobTechStack.class
                ).getResultList();

            if (technologies.isEmpty()) {

                System.out.println("No technologies found.");

            } else {

                for (JobTechStack tech : technologies) {
                    System.out.println(tech);
                }
            }

        } catch (Exception e) {

            System.out.println("Could not read technologies: "
                + e.getMessage());
        }
    }

    // READ BY POST ID
    public void getTechnologiesByPostId(int postId) {

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            List<JobTechStack> technologies =
                session.createQuery(
                        "FROM JobTechStack WHERE postId = :postId ORDER BY techId",
                        JobTechStack.class
                    ).setParameter("postId", postId)
                    .getResultList();

            if (technologies.isEmpty()) {

                System.out.println(
                    "No technologies found for this post.");

            } else {

                for (JobTechStack tech : technologies) {
                    System.out.println(tech);
                }
            }

        } catch (Exception e) {

            System.out.println("Could not read technologies: "
                + e.getMessage());
        }
    }

    // UPDATE
    public void updateTechnology(JobTechStack tech) {

        Transaction transaction = null;

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.merge(tech);

            transaction.commit();

            System.out.println("Technology updated successfully.");

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println("Could not update technology: "
                + e.getMessage());
        }
    }

    // DELETE
    public void deleteTechnology(int techId) {

        Transaction transaction = null;

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            JobTechStack tech =
                session.get(JobTechStack.class, techId);

            if (tech != null) {

                session.remove(tech);

                transaction.commit();

                System.out.println(
                    "Technology deleted successfully.");

            } else {

                transaction.rollback();

                System.out.println("Technology not found.");
            }

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println("Could not delete technology: "
                + e.getMessage());
        }
    }
}