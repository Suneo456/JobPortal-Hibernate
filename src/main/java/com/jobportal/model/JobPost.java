package com.jobportal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "job_post")
public class JobPost {

    @Id
    @Column(name = "post_id")
    private int postId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "post_profile")
    private String postProfile;

    @Column(name = "post_desc")
    private String postDesc;

    @Column(name = "req_experience")
    private Integer reqExperience;

    public JobPost() {
    }

    public JobPost(int postId, int userId, String postProfile,
                   String postDesc, Integer reqExperience) {

        this.postId = postId;
        this.userId = userId;
        this.postProfile = postProfile;
        this.postDesc = postDesc;
        this.reqExperience = reqExperience;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPostProfile() {
        return postProfile;
    }

    public void setPostProfile(String postProfile) {
        this.postProfile = postProfile;
    }

    public String getPostDesc() {
        return postDesc;
    }

    public void setPostDesc(String postDesc) {
        this.postDesc = postDesc;
    }

    public Integer getReqExperience() {
        return reqExperience;
    }

    public void setReqExperience(Integer reqExperience) {
        this.reqExperience = reqExperience;
    }

    @Override
    public String toString() {
        return "JobPost{" +
            "postId=" + postId +
            ", userId=" + userId +
            ", postProfile='" + postProfile + '\'' +
            ", postDesc='" + postDesc + '\'' +
            ", reqExperience=" + reqExperience +
            '}';
    }
}