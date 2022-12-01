package com.sinhvien.myapplication.authentication;

public class Auth {
    public static boolean isAdmin = false;
    public static boolean isUser = false;

    public Auth(){}

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean getIsUser() {
        return isUser;
    }

    public void setIsUser(boolean isUser) {
        this.isUser = isUser;
    }
}
