package cdap.questiongenerator.question;

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

    public Answer() {
        this.correct = true;
        this.id = "a";
    }

    public Answer(String value) {
        this.id = "a";
        this.value = value;
        this.correct = true;
    }
}
