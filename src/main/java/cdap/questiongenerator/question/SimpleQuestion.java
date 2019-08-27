package cdap.questiongenerator.question;

import java.util.List;

public class SimpleQuestion {
    private int questionNo;
    private String question;
    private List<Answer> answers;

    public SimpleQuestion(int questionNo, String question, List<Answer> answers) {
        this.questionNo = questionNo;
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
