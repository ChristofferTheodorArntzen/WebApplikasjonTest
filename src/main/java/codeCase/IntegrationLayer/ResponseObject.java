package codeCase.IntegrationLayer;

public class ResponseObject {

    private Integer insuranceId;
    private Boolean insuranceStatus;

    public ResponseObject() {
    }

    public ResponseObject(Integer insuranceId, Boolean insuranceStatus) {
        this.insuranceId = insuranceId;
        this.insuranceStatus = insuranceStatus;
    }

    public Integer getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Integer insuranceId) {
        this.insuranceId = insuranceId;
    }

    public Boolean getInsuranceStatus() {
        return insuranceStatus;
    }

    public void setInsuranceStatus(Boolean insuranceStatus) {
        this.insuranceStatus = insuranceStatus;
    }
}
