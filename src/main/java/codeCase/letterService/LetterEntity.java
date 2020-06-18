package codeCase.letterService;

public class LetterEntity {

    private Integer userId;
    private Integer insuranceId;
    private String letterContent;
    private boolean sent;

    public LetterEntity() { }

    public LetterEntity(Integer userId, Integer insuranceId, String letterContent, boolean sent) {
        this.userId = userId;
        this.insuranceId = insuranceId;
        this.letterContent = letterContent;
        this.sent = sent;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Integer insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getLetterContent() {
        return letterContent;
    }

    public void setLetterContent(String letterContent) {
        this.letterContent = letterContent;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }
}
