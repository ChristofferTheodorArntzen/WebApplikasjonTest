package codeCase.IntegrationLayer;

public class UserAndInsurance {

    private String insuranceType;
    private String desc;
    private String name;
    private String address;
    private int age;

    public UserAndInsurance() {
    }

    public UserAndInsurance(String insuranceType, String desc, String name, String address, int age) {
        this.insuranceType = insuranceType;
        this.desc = desc;
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
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
