package cdap.entities;

import cdap.questiongenerator.question.SimpleQuestion;

import java.util.List;

public class Questions {
    private String lectureId;
    private List<SimpleQuestion> questions;
    private int count;

    public Questions(String lectureId) {
        this.lectureId = lectureId;
    }

    public String getLectureId() {
        return lectureId;
    }

    public void setLectureId(String lectureId) {
        this.lectureId = lectureId;
    }

    public List<SimpleQuestion> getQuestions() {
        return questions;
    }

    public int getCount() {
        return count;
    }

    public void setQuestions(List<SimpleQuestion> questions) {
        this.questions = questions;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
