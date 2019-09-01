package cdap.questiongenerator.question;

import org.apache.commons.lang.RandomStringUtils;

public class Answer {
    private String id;
    private String value;
    private boolean correct;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Answer() {
        this.correct = true;
        this.id = RandomStringUtils.randomAlphanumeric(8);
    }

    public Answer(String value) {
        this.id = RandomStringUtils.randomAlphanumeric(8);
        this.value = value;
        this.correct = true;
    }
}
