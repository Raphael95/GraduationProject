package dao;

/**
 * Created by Rapha on 2017/3/30.
 */

public class User {

    private int id;
    private String account;
    private String password;
    private String name;
    private int age;
    private String email;
    private String auth;
    private String departId;
    private String description;
    private String flag;

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getAuth() {
        return auth;
    }

    public String getDepartId() {
        return departId;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getFlag() {
        return flag;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
