package com.jobportal;

import com.jobportal.Entity.JobPostDAO;
import com.jobportal.Entity.JobTechStackDAO;
import com.jobportal.Entity.UserDAO.UserDAO;
import com.jobportal.config.HibernateUtil;
import com.jobportal.model.JobPost;
import com.jobportal.model.JobTechStack;
import com.jobportal.model.User;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static UserDAO userDAO = new UserDAO();
    static JobPostDAO jobPostDAO = new JobPostDAO();
    static JobTechStackDAO techDAO = new JobTechStackDAO();

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n=================================");
            System.out.println("        JOB PORTAL SYSTEM");
            System.out.println("=================================");
            System.out.println("1. User Management");
            System.out.println("2. Job Post Management");
            System.out.println("3. Technology Management");
            System.out.println("4. Exit");
            System.out.println("=================================");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    userMenu();
                    break;

                case 2:
                    jobPostMenu();
                    break;

                case 3:
                    technologyMenu();
                    break;

                case 4:
                    System.out.println("\nThank you for using Job Portal!");
                    HibernateUtil.shutdown();
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 4);

        sc.close();
    }


    // =========================================================
    // USER MENU
    // =========================================================

    public static void userMenu() {

        int choice;

        do {

            System.out.println("\n=================================");
            System.out.println("        USER MANAGEMENT");
            System.out.println("=================================");
            System.out.println("1. Add User");
            System.out.println("2. View All Users");
            System.out.println("3. Search User by ID");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("6. Back to Main Menu");
            System.out.println("=================================");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addUser();
                    break;

                case 2:
                    userDAO.getAllUsers();
                    break;

                case 3:
                    System.out.print("Enter User ID: ");
                    int id = sc.nextInt();

                    userDAO.getUserById(id);
                    break;

                case 4:
                    updateUser();
                    break;

                case 5:
                    System.out.print("Enter User ID to delete: ");
                    int deleteId = sc.nextInt();

                    userDAO.deleteUser(deleteId);
                    break;

                case 6:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }


    // =========================================================
    // ADD USER
    // =========================================================

    public static void addUser() {

        System.out.println("\n--------- ADD USER ---------");

        System.out.print("Enter User ID: ");
        int id = sc.nextInt();

        System.out.print("Enter Username: ");
        String username = sc.next();

        System.out.print("Enter Password: ");
        String password = sc.next();

        User user = new User(id, username, password);

        userDAO.addUser(user);
    }


    // =========================================================
    // UPDATE USER
    // =========================================================

    public static void updateUser() {

        System.out.println("\n--------- UPDATE USER ---------");

        System.out.print("Enter User ID: ");
        int id = sc.nextInt();

        User user = userDAO.getUserById(id);

        if (user != null) {

            System.out.print("Enter New Username: ");
            String username = sc.next();

            System.out.print("Enter New Password: ");
            String password = sc.next();

            user.setUsername(username);
            user.setPassword(password);

            userDAO.updateUser(user);

        } else {

            System.out.println("User not found.");
        }
    }


    // =========================================================
    // JOB POST MENU
    // =========================================================

    public static void jobPostMenu() {

        int choice;

        do {

            System.out.println("\n=================================");
            System.out.println("       JOB POST MANAGEMENT");
            System.out.println("=================================");
            System.out.println("1. Add Job Post");
            System.out.println("2. View All Job Posts");
            System.out.println("3. Search Job Post by ID");
            System.out.println("4. Update Job Post");
            System.out.println("5. Delete Job Post");
            System.out.println("6. Back to Main Menu");
            System.out.println("=================================");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addJobPost();
                    break;

                case 2:
                    jobPostDAO.getAllJobPosts()
                        .forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Enter Job Post ID: ");
                    int postId = sc.nextInt();

                    JobPost job = jobPostDAO.getJobPostById(postId);

                    if (job != null) {
                        System.out.println(job);
                    } else {
                        System.out.println("Job Post not found.");
                    }

                    break;

                case 4:
                    updateJobPost();
                    break;

                case 5:
                    System.out.print("Enter Job Post ID to delete: ");
                    int deletePostId = sc.nextInt();

                    jobPostDAO.deleteJobPost(deletePostId);
                    break;

                case 6:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }


    // =========================================================
    // ADD JOB POST
    // =========================================================

    public static void addJobPost() {

        System.out.println("\n--------- ADD JOB POST ---------");

        System.out.print("Enter Post ID: ");
        int postId = sc.nextInt();

        System.out.print("Enter User ID: ");
        int userId = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Job Profile: ");
        String profile = sc.nextLine();

        System.out.print("Enter Job Description: ");
        String description = sc.nextLine();

        System.out.print("Enter Required Experience: ");
        int experience = sc.nextInt();

        JobPost jobPost = new JobPost(
            postId,
            userId,
            profile,
            description,
            experience
        );

        jobPostDAO.addJobPost(jobPost);
    }


    // =========================================================
    // UPDATE JOB POST
    // =========================================================

    public static void updateJobPost() {

        System.out.println("\n--------- UPDATE JOB POST ---------");

        System.out.print("Enter Post ID: ");
        int postId = sc.nextInt();

        JobPost job = jobPostDAO.getJobPostById(postId);

        if (job != null) {

            sc.nextLine();

            System.out.print("Enter New Job Profile: ");
            String profile = sc.nextLine();

            System.out.print("Enter New Job Description: ");
            String description = sc.nextLine();

            System.out.print("Enter New Required Experience: ");
            int experience = sc.nextInt();

            job.setPostProfile(profile);
            job.setPostDesc(description);
            job.setReqExperience(experience);

            jobPostDAO.updateJobPost(job);

        } else {

            System.out.println("Job Post not found.");
        }
    }


    // =========================================================
    // TECHNOLOGY MENU
    // =========================================================

    public static void technologyMenu() {

        int choice;

        do {

            System.out.println("\n=================================");
            System.out.println("       TECHNOLOGY MANAGEMENT");
            System.out.println("=================================");
            System.out.println("1. Add Technology");
            System.out.println("2. View All Technologies");
            System.out.println("3. View Technologies by Job Post ID");
            System.out.println("4. Update Technology");
            System.out.println("5. Delete Technology");
            System.out.println("6. Back to Main Menu");
            System.out.println("=================================");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addTechnology();
                    break;

                case 2:
                    techDAO.getAllTechnologies();
                    break;

                case 3:
                    System.out.print("Enter Job Post ID: ");
                    int postId = sc.nextInt();

                    techDAO.getTechnologiesByPostId(postId);
                    break;

                case 4:
                    updateTechnology();
                    break;

                case 5:
                    System.out.print("Enter Technology ID to delete: ");
                    int techId = sc.nextInt();

                    techDAO.deleteTechnology(techId);
                    break;

                case 6:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }


    // =========================================================
    // ADD TECHNOLOGY
    // =========================================================

    public static void addTechnology() {

        System.out.println("\n--------- ADD TECHNOLOGY ---------");

        System.out.print("Enter Technology ID: ");
        int techId = sc.nextInt();

        System.out.print("Enter Job Post ID: ");
        int postId = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Technology: ");
        String technology = sc.nextLine();

        JobTechStack tech = new JobTechStack(
            techId,
            postId,
            technology
        );

        techDAO.addTechnology(tech);
    }


    // =========================================================
    // UPDATE TECHNOLOGY
    // =========================================================

    public static void updateTechnology() {

        System.out.println("\n--------- UPDATE TECHNOLOGY ---------");

        System.out.print("Enter Technology ID: ");
        int techId = sc.nextInt();

        System.out.print("Enter Job Post ID: ");
        int postId = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter New Technology: ");
        String technology = sc.nextLine();

        JobTechStack tech = new JobTechStack(
            techId,
            postId,
            technology
        );

        techDAO.updateTechnology(tech);
    }
}