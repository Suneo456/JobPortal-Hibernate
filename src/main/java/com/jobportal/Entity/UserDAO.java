package com.jobportal.Entity.UserDAO;

import com.jobportal.config.HibernateUtil;
import com.jobportal.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAO {

    // CREATE
    public void addUser(User user) {

        Transaction transaction = null;

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(user);

            transaction.commit();

            System.out.println("User added successfully.");

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println("Could not add user: "
                + e.getMessage());
        }
    }

    // READ ALL
    public void getAllUsers() {

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            List<User> users =
                session.createQuery(
                    "FROM User ORDER BY id",
                    User.class
                ).getResultList();

            if (users.isEmpty()) {

                System.out.println("No users found.");

            } else {

                for (User user : users) {
                    System.out.println(user);
                }
            }

        } catch (Exception e) {

            System.out.println("Could not read users: "
                + e.getMessage());
        }
    }

    // READ BY ID
    public User getUserById(int id) {

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            User user = session.get(User.class, id);

            if (user != null) {

                System.out.println(user);

            } else {

                System.out.println("User not found.");
            }

            return user;

        } catch (Exception e) {

            System.out.println("Could not find user: "
                + e.getMessage());

            return null;
        }
    }

    // UPDATE
    public void updateUser(User user) {

        Transaction transaction = null;

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.merge(user);

            transaction.commit();

            System.out.println("User updated successfully.");

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println("Could not update user: "
                + e.getMessage());
        }
    }

    // DELETE
    public void deleteUser(int id) {

        Transaction transaction = null;

        try (Session session =
                 HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            User user = session.get(User.class, id);

            if (user != null) {

                session.remove(user);

                transaction.commit();

                System.out.println("User deleted successfully.");

            } else {

                transaction.rollback();

                System.out.println("User not found.");
            }

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println(
                "Could not delete user: " + e.getMessage());

            System.out.println(
                "Hint: delete the user's job posts and their technologies first.");
        }
    }
}