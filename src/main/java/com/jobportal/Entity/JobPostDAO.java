package com.jobportal.Entity;

import com.jobportal.config.HibernateUtil;
import com.jobportal.model.JobPost;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class JobPostDAO {

    public void addJobPost(JobPost jobPost) {

        Transaction transaction = null;

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(jobPost);

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    public JobPost getJobPostById(int postId) {

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            return session.get(JobPost.class, postId);
        }
    }

    public List<JobPost> getAllJobPosts() {

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            return session
                .createQuery("FROM JobPost", JobPost.class)
                .getResultList();
        }
    }

    public void updateJobPost(JobPost jobPost) {

        Transaction transaction = null;

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.merge(jobPost);

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    public void deleteJobPost(int postId) {

        Transaction transaction = null;

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            JobPost jobPost =
                session.get(JobPost.class, postId);

            if (jobPost != null) {
                session.remove(jobPost);
            }

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }
}