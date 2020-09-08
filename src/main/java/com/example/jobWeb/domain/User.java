package com.example.jobWeb.domain;

import java.io.Serializable;

/**
 * zj_users 用户
 */
public class User implements Serializable {
    private static final long serialVersionUID = 3763178303336308927L;
    private int user_id, user_money,isused,user_frozen_money;
    private String user_name, user_password,zfb_name,zfb_number,user_tel;

    public User() {
    }

    public User(int user_id, int user_money, int isused, int user_frozen_money, String user_name, String user_password, String zfb_name, String zfb_number, String user_tel) {
        this.user_id = user_id;
        this.user_money = user_money;
        this.isused = isused;
        this.user_frozen_money = user_frozen_money;
        this.user_name = user_name;
        this.user_password = user_password;
        this.zfb_name = zfb_name;
        this.zfb_number = zfb_number;
        this.user_tel = user_tel;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public int getUser_frozen_money() {
        return user_frozen_money;
    }

    public void setUser_frozen_money(int user_frozen_money) {
        this.user_frozen_money = user_frozen_money;
    }

    public String getZfb_name() {
        return zfb_name;
    }

    public void setZfb_name(String zfb_name) {
        this.zfb_name = zfb_name;
    }

    public String getZfb_number() {
        return zfb_number;
    }

    public void setZfb_number(String zfb_number) {
        this.zfb_number = zfb_number;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_money=" + user_money +
                ", isused=" + isused +
                ", user_frozen_money=" + user_frozen_money +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", zfb_name='" + zfb_name + '\'' +
                ", zfb_number='" + zfb_number + '\'' +
                '}';
    }

    public User(String user_name, String user_password) {
        this.user_name = user_name;
        this.user_password = user_password;
    }

    public int getIsused() {
        return isused;
    }

    public void setIsused(int isused) {
        this.isused = isused;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_money() {
        return user_money;
    }

    public void setUser_money(int user_money) {
        this.user_money = user_money;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
