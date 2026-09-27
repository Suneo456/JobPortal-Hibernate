package com.jobportal.service;

import com.jobportal.Entity.JobPostDAO;
import com.jobportal.Entity.JobTechStackDAO;
import com.jobportal.Entity.UserSkillDAO;
import com.jobportal.model.JobPost;
import com.jobportal.model.JobTechStack;
import com.jobportal.model.UserSkill;

import java.util.*;

public class JobRecommendationService {

  private UserSkillDAO userSkillDAO = new UserSkillDAO();
  private JobPostDAO jobPostDAO = new JobPostDAO();
  private JobTechStackDAO techDAO = new JobTechStackDAO();

  public void recommendJobs(int userId) {

    List<UserSkill> userSkills =
        userSkillDAO.getSkillsByUserId(userId);

    if (userSkills.isEmpty()) {

      System.out.println("\nNo skills found for this user.");
      System.out.println("Please add skills first.");

      return;
    }

    Set<String> userSkillSet = new HashSet<>();

    for (UserSkill skill : userSkills) {

      userSkillSet.add(
          skill.getSkill().trim().toLowerCase()
      );
    }

    List<JobPost> jobs = jobPostDAO.getAllJobPosts();

    List<JobRecommendation> recommendations =
        new ArrayList<>();

    for (JobPost job : jobs) {

      List<JobTechStack> technologies =
          techDAO.getTechnologiesByPostId(job.getPostId());

      if (technologies == null || technologies.isEmpty()) {
        continue;
      }

      int matchedSkills = 0;

      for (JobTechStack tech : technologies) {

        String technology =
            tech.getTechnology().trim().toLowerCase();

        if (userSkillSet.contains(technology)) {
          matchedSkills++;
        }
      }

      double matchPercentage =
          ((double) matchedSkills / technologies.size()) * 100;

      recommendations.add(
          new JobRecommendation(
              job,
              matchPercentage
          )
      );
    }

    recommendations.sort(
        Comparator.comparingDouble(
            JobRecommendation::getMatchPercentage
        ).reversed()
    );

    System.out.println("\n======================================");
    System.out.println("       SMART JOB RECOMMENDATIONS");
    System.out.println("======================================");

    for (JobRecommendation recommendation : recommendations) {

      System.out.println(
          "\nJob ID: " +
              recommendation.getJob().getPostId()
      );

      System.out.println(
          "Profile: " +
              recommendation.getJob().getPostProfile()
      );

      System.out.printf(
          "Match: %.2f%%%n",
          recommendation.getMatchPercentage()
      );

      System.out.println(
          "Description: " +
              recommendation.getJob().getPostDesc()
      );
    }

    System.out.println("\n======================================");
  }

  static class JobRecommendation {

    private JobPost job;
    private double matchPercentage;

    public JobRecommendation(
        JobPost job,
        double matchPercentage) {

      this.job = job;
      this.matchPercentage = matchPercentage;
    }

    public JobPost getJob() {
      return job;
    }

    public double getMatchPercentage() {
      return matchPercentage;
    }
  }
}