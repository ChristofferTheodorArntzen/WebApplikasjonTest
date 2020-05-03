package codeCaseFremtind.subjectSystem;

public class Agreement {

    private Integer id;
    private String agreementType;
    private boolean status;
    private String desc;

    public Agreement() { }

    public Agreement(String agreementType, boolean status, String desc) {
        this.agreementType = agreementType;
        this.status = status;
        this.desc = desc;
    }

    public Agreement(Integer id, String agreementType, boolean status, String desc) {
        this.id = id;
        this.agreementType = agreementType;
        this.status = status;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgreementType() {
        return agreementType;
    }

    public void setAgreementType(String agreementType) {
        this.agreementType = agreementType;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
