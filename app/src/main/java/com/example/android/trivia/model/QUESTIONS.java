package com.example.android.trivia.model;

public class QUESTIONS {
    private String answer;
    private Boolean answertrue;


    public QUESTIONS(String answer, Boolean answertrue) {
        this.answer = answer;
        this.answertrue = answertrue;
    }

    public QUESTIONS() {
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getAnswertrue() {
        return answertrue;
    }

    public void setAnswertrue(Boolean answertrue) {
        this.answertrue = answertrue;
    }

    @Override
    public String toString() {
        return "QUESTIONS{" +
                "answer='" + answer + '\'' +
                ", answertrue=" + answertrue +
                '}';
    }
}
