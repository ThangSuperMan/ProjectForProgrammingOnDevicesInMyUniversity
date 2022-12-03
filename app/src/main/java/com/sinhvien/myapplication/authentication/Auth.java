package com.sinhvien.myapplication.authentication;

import com.sinhvien.myapplication.schemas.User;

public class Auth {
    public static boolean isAdmin = false;
    public static boolean isUser = false;
    public static User user = new User();

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
