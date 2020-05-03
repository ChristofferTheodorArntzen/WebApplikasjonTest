package codeCaseFremtind.letterService;

public class LetterEntity {

    private Integer id;
    private Integer userId;
    private Integer agreementId;
    private boolean status;

    public LetterEntity() { }

    public LetterEntity(Integer id, Integer userId, Integer agreementId, boolean status) {
        this.id = id;
        this.userId = userId;
        this.agreementId = agreementId;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(Integer agreementId) {
        this.agreementId = agreementId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
