package codeCaseFremtind.IntegrationLayer;

public class UserAndAgreement {

    private String agreementType;
    private String desc;
    private String name;
    private String address;
    private int age;

    public UserAndAgreement() {
    }

    public UserAndAgreement(String agreementType, String desc, String name, String address, int age) {
        this.agreementType = agreementType;
        this.desc = desc;
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public String getAgreementType() {
        return agreementType;
    }

    public void setAgreementType(String agreementType) {
        this.agreementType = agreementType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
