package model;

public class ReturnBookDetails {
    private String memberId;
    private String email;
    private String phone;
    private String isbn;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public ReturnBookDetails(String memberId, String email, String phone, String isbn) {
        this.memberId = memberId;
        this.email = email;
        this.phone = phone;
        this.isbn = isbn;
    }

    public ReturnBookDetails() {
    }
}
