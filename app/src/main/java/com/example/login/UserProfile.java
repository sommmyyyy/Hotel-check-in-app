package com.example.login;

public class UserProfile
{
    public String userAge;
    public String userFname;
    public String userLname;
    public String userEmail;
    public String userPhone;
    public String userAdults;
    public String userChild;

    public UserProfile(String userFname, String userLname, String userPhone, String userEmail, String userAge, String userAdults, String userChild)
    {
        this.userAge=userAge;
        this.userEmail=userEmail;
        this.userFname=userFname;
        this.userLname=userLname;
        this.userPhone=userPhone;
        this.userAdults=userAdults;
        this.userChild=userChild;
    }
}