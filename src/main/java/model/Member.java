package model;

public class Member {
    private String id;
    private String name;
    private String contactNo;
    private String email;
    private String address;
    private String status;

    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Member(String id, String name, String contactNo, String email, String address, String status) {
        this.id = id;
        this.name = name;
        this.contactNo = contactNo;
        this.email = email;
        this.address = address;
        this.status = status;
    }

    public Member() {
    }
}
