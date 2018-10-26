package dao;

import org.litepal.crud.DataSupport;

/**
 * Created by Rapha on 2017/3/30.
 */

public class User extends DataSupport{

    private Integer userId;
    private String password;
    private String real_name;
    private Integer gender;
    private String phone;
    private String email;
    private Integer userRank;
    private Integer deptId;


    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserRank(Integer userRank) {
        this.userRank = userRank;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getReal_name() {
        return real_name;
    }

    public Integer getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Integer getUserRank() {
        return userRank;
    }

    public Integer getDeptId() {
        return deptId;
    }




}
