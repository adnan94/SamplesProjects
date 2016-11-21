package com.example.adnan.myapplication;

/**
 * Created by Adnan on 8/29/2015.
 */
public class docs {
    String type;
    String company;
    String skill;
    String education;
    String description;
    String salary;
    String job;

    public docs(String type, String company, String skill, String education, String description, String salary, String job) {
        this.type = type;
        this.company = company;
        this.skill = skill;
        this.education = education;
        this.description = description;
        this.salary = salary;
        this.job = job;
    }

    docs() {

    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSkill() {
        return skill;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
