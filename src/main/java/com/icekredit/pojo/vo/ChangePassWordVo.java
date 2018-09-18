package com.icekredit.pojo.vo;

public class ChangePassWordVo {
    private String oldPasswd;

    private String newPasswd;

    private String passwdCheck;

    public String getOldPasswd() {
        return oldPasswd;
    }

    public void setOldPasswd(String oldPasswd) {
        this.oldPasswd = oldPasswd;
    }

    public String getNewPasswd() {
        return newPasswd;
    }

    public void setNewPasswd(String newPasswd) {
        this.newPasswd = newPasswd;
    }

    public String getPasswdCheck() {
        return passwdCheck;
    }

    public void setPasswdCheck(String passwdCheck) {
        this.passwdCheck = passwdCheck;
    }
}
