package cdap.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionGeneratorRequest {
    public String getLectureId() {
        return lectureId;
    }

    public void setLectureId(String lectureId) {
        this.lectureId = lectureId;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public QuestionGeneratorRequest(@JsonProperty("lectureId") String lectureId, @JsonProperty("document") String document) {
        this.lectureId = lectureId;
        this.document = document;
    }

    private String lectureId;
    private String document;
}
