package codeCase.fagsystem.insurance;

public class Insurance {

    private Integer id;
    private String insuranceType;
    private boolean status;
    private String desc;

    public Insurance() { }

    public Insurance(String insuranceType, boolean status, String desc) {
        this.insuranceType = insuranceType;
        this.status = status;
        this.desc = desc;
    }

    public Insurance(Integer id, String insuranceType, boolean status, String desc) {
        this.id = id;
        this.insuranceType = insuranceType;
        this.status = status;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
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

    @Override
    public String toString() {
        return "Insurance{" +
                "id=" + id +
                ", insuranceType='" + insuranceType + '\'' +
                ", status=" + status +
                ", desc='" + desc + '\'' +
                '}';
    }
}
