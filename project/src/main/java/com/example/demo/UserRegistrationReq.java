package com.example.demo;

public class UserRegistrationReq {
    private  String email;
    private String otp;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
    public User toUserEntity(){
        User user =new User();
        user.setEmail(this.email);
        return user;
    }
}
