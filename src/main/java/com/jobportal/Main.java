package com.jobportal;

import com.jobportal.dao.JobPostDAO;
import com.jobportal.dao.JobTechStackDAO;
import com.jobportal.dao.UserDAO;
import com.jobportal.model.JobPost;
import com.jobportal.model.JobTechStack;
import com.jobportal.model.User;

import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    private static final UserDAO userDAO = new UserDAO();
    private static final JobPostDAO jobPostDAO = new JobPostDAO();
    private static final JobTechStackDAO techDAO = new JobTechStackDAO();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n========== JOB PORTAL JDBC CRUD ==========");
            System.out.println("1. User CRUD");
            System.out.println("2. Job Post CRUD");
            System.out.println("3. Job Tech Stack CRUD");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    userMenu();
                    break;
                case 2:
                    jobPostMenu();
                    break;
                case 3:
                    techMenu();
                    break;
                case 4:
                    System.out.println("Application closed.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void userMenu() {
        while (true) {
            System.out.println("\n---------- USER CRUD ----------");
            System.out.println("1. Add User");
            System.out.println("2. View All Users");
            System.out.println("3. View User By ID");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("6. Back");
            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = readInt();
                    System.out.print("Enter username: ");
                    String username = sc.nextLine();
                    System.out.print("Enter password: ");
                    String password = sc.nextLine();

                    userDAO.addUser(new User(id, username, password));
                    break;

                case 2:
                    userDAO.getAllUsers();
                    break;

                case 3:
                    System.out.print("Enter user ID: ");
                    userDAO.getUserById(readInt());
                    break;

                case 4:
                    System.out.print("Enter user ID: ");
                    int updateId = readInt();
                    System.out.print("Enter new username: ");
                    String newUsername = sc.nextLine();
                    System.out.print("Enter new password: ");
                    String newPassword = sc.nextLine();

                    userDAO.updateUser(
                            new User(updateId, newUsername, newPassword));
                    break;

                case 5:
                    System.out.print("Enter user ID: ");
                    userDAO.deleteUser(readInt());
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void jobPostMenu() {
        while (true) {
            System.out.println("\n---------- JOB POST CRUD ----------");
            System.out.println("1. Add Job Post");
            System.out.println("2. View All Job Posts");
            System.out.println("3. View Job Post By ID");
            System.out.println("4. Update Job Post");
            System.out.println("5. Delete Job Post");
            System.out.println("6. Back");
            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter post ID: ");
                    int postId = readInt();

                    System.out.print("Enter user ID: ");
                    int userId = readInt();

                    System.out.print("Enter profile: ");
                    String profile = sc.nextLine();

                    System.out.print("Enter description: ");
                    String description = sc.nextLine();

                    System.out.print("Enter required experience (years), or -1 for NULL: ");
                    int experience = readInt();

                    Integer reqExperience =
                            experience == -1 ? null : experience;

                    jobPostDAO.addJobPost(
                            new JobPost(postId, userId, profile,
                                    description, reqExperience));
                    break;

                case 2:
                    jobPostDAO.getAllJobPosts();
                    break;

                case 3:
                    System.out.print("Enter post ID: ");
                    jobPostDAO.getJobPostById(readInt());
                    break;

                case 4:
                    System.out.print("Enter post ID: ");
                    int updatePostId = readInt();

                    System.out.print("Enter new profile: ");
                    String newProfile = sc.nextLine();

                    System.out.print("Enter new description: ");
                    String newDescription = sc.nextLine();

                    System.out.print("Enter new experience (years), or -1 for NULL: ");
                    int newExperience = readInt();

                    Integer newReqExperience =
                            newExperience == -1 ? null : newExperience;

                    // user_id is not changed by this update.
                    jobPostDAO.updateJobPost(
                            new JobPost(updatePostId, 0, newProfile,
                                    newDescription, newReqExperience));
                    break;

                case 5:
                    System.out.print("Enter post ID: ");
                    jobPostDAO.deleteJobPost(readInt());
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void techMenu() {
        while (true) {
            System.out.println("\n---------- JOB TECH STACK CRUD ----------");
            System.out.println("1. Add Technology");
            System.out.println("2. View All Technologies");
            System.out.println("3. View Technologies By Post ID");
            System.out.println("4. Update Technology");
            System.out.println("5. Delete Technology");
            System.out.println("6. Back");
            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter tech ID: ");
                    int techId = readInt();

                    System.out.print("Enter post ID: ");
                    int postId = readInt();

                    System.out.print("Enter technology: ");
                    String technology = sc.nextLine();

                    techDAO.addTechnology(
                            new JobTechStack(techId, postId, technology));
                    break;

                case 2:
                    techDAO.getAllTechnologies();
                    break;

                case 3:
                    System.out.print("Enter post ID: ");
                    techDAO.getTechnologiesByPostId(readInt());
                    break;

                case 4:
                    System.out.print("Enter tech ID: ");
                    int updateTechId = readInt();

                    System.out.print("Enter new technology: ");
                    String newTechnology = sc.nextLine();

                    techDAO.updateTechnology(
                            new JobTechStack(updateTechId, 0, newTechnology));
                    break;

                case 5:
                    System.out.print("Enter tech ID: ");
                    techDAO.deleteTechnology(readInt());
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static int readInt() {
        while (true) {
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid integer: ");
            }
        }
    }
}
