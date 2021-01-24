package com.cdac.ELearning.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Problem {

    String problemName;
    String difficulty;
    String tags;
    String description;
    List<HashMap<String,String>> testCases = new ArrayList<>();

    public Problem() { }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<HashMap<String, String>> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<HashMap<String, String>> testCases) {
        this.testCases = testCases;
    }
}

