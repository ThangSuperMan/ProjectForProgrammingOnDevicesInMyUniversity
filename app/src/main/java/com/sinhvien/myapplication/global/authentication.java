package com.sinhvien.myapplication.global;

class Authentication {
    private boolean isAdmin = false;
    private boolean isUser = false;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }
}
