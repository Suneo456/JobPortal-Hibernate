package com.jobportal.model;

public class JobTechStack {

    private int techId;
    private int postId;
    private String technology;

    public JobTechStack() {
    }

    public JobTechStack(int techId, int postId, String technology) {
        this.techId = techId;
        this.postId = postId;
        this.technology = technology;
    }

    public int getTechId() {
        return techId;
    }

    public void setTechId(int techId) {
        this.techId = techId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    @Override
    public String toString() {
        return "JobTechStack{" +
                "techId=" + techId +
                ", postId=" + postId +
                ", technology='" + technology + '\'' +
                '}';
    }
}
