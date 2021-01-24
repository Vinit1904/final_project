package com.cdac.ELearning.model;


public class Score {

    String quizScore;
    String problemScore;

    public Score(){
        this.quizScore = "";
        this.problemScore = "";
    }

    public String getQuizScore() {
        return quizScore;
    }

    public void setQuizScore(String quizScore) {
        this.quizScore = quizScore;
    }

    public String getProblemScore() {
        return problemScore;
    }

    public void setProblemScore(String problemScore) {
        this.problemScore = problemScore;
    }
}
