package codeCaseFremtind.IntegrationLayer;

public class ResponseObject {

    private Integer agreementId;
    private Boolean agreementStatus;

    public ResponseObject() {
    }

    public ResponseObject(Integer agreementId, Boolean agreementStatus) {
        this.agreementId = agreementId;
        this.agreementStatus = agreementStatus;
    }

    public Integer getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(Integer agreementId) {
        this.agreementId = agreementId;
    }

    public Boolean getAgreementStatus() {
        return agreementStatus;
    }

    public void setAgreementStatus(Boolean agreementStatus) {
        this.agreementStatus = agreementStatus;
    }
}
