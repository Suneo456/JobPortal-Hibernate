package com.jobportal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_skill")
public class UserSkill {

  @Id
  @Column(name = "skill_id")
  private int skillId;

  @Column(name = "user_id")
  private int userId;

  @Column(name = "skill")
  private String skill;

  public UserSkill() {
  }

  public UserSkill(int skillId, int userId, String skill) {
    this.skillId = skillId;
    this.userId = userId;
    this.skill = skill;
  }

  public int getSkillId() {
    return skillId;
  }

  public void setSkillId(int skillId) {
    this.skillId = skillId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getSkill() {
    return skill;
  }

  public void setSkill(String skill) {
    this.skill = skill;
  }

  @Override
  public String toString() {
    return "UserSkill{" +
        "skillId=" + skillId +
        ", userId=" + userId +
        ", skill='" + skill + '\'' +
        '}';
  }
}